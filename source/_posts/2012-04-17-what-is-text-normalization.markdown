---
layout: post
title: "What is Text Normalization?"
date: 2012-04-17 09:33
comments: true
categories: 
- Programming
- iOS
- Mac OS X
---

In my [previous post][PP] I mentioned that some of the word counting approaches may be suitable if the input text had been normalized, but I didn't really elaborate on what this means. According to [Wikipedia][W1]:

> Text normalization is a process by which text is transformed in some way to make it consistent in a way which it might not have been before.

The article also gives some examples of the kind of transformations that are commonly performed. Of necessity, any normalization process is going to be application specific, but let's assume for the sake of example that the word count is intended to be used in a writing application of some sort (a text editor or word processor). Given that we probably don't care about [Unicode normalization][UN], and definitely don't care about anything which would change the words such as stemming or canonicalization. But maybe we could normalize all runs of whitespace into single spaces? Our original test string then changes from "Peter  piper  picked  a  peck  of  pickled  pepper . No — really — he did!" to "Peter piper picked a peck of pickled pepper . No — really — he did!". The difference is probably hard to spot, but all of the doubled spaces in the first string have been replaces with single spaces, and the hair-spaces have been replaced with regular spaces.

How do the different word counting functions work now?

{:.tabular}
|--------------------|-------|------------|
| Method             | Raw   | Normalized |
|--------------------|------:|-----------:|
| Original Scanner   |    15 |         15 |
| Regular Expression |    12 |         12 |
| String Components  |    18 |         15 |
| Char Components    |    22 |         15 |
| Linguistic tagger  |    12 |         12 |
|--------------------|-------|------------|

Better, but it still only leaves the same two functions returning the correct result (assuming of course that you *don't* want to count strings of puncuation, this may or may not be the case in a code editor for example).

I can't speak for the inner working of the linguistic tagger, but the reason that the regex based function works is that it is basing it's approach on a *whitelist* rather than a *blacklist*. The regex basically says "these are valid word characters, everything else can be ignored" whereas all of the other functions take the stance "these are whitespace, eveything else must be part of a word". Anybody who has done any web development or input validation generally will tell you that whitelists are almost always the correct approach to take. It's just easier to enumerate all of the valid values for a given set than to try to list all of the exceptions.

## Linguistic Tagger

There are quite a few more options available for analysing text here, let's start by counting sentences as well as words, this can be done by adding a count for sentences and keeping track of the current sentence based on it's starting location. The interesting code is on lines 9 and 10:

```objc
__block NSUInteger words = 0;
__block NSUInteger sentences = 0;
__block NSUInteger current_sentence = 0;
[tagger enumerateTagsInRange:NSMakeRange(0, [string length])
                      scheme:NSLinguisticTagSchemeTokenType
                     options:0
                  usingBlock:^(NSString* tag, NSRange token, NSRange sentence, BOOL *stop) {
  if ([tag isEqual:NSLinguisticTagWord]) ++words;
  if (!sentences || current_sentence != sentence.location) ++sentences;
  current_sentence = sentence.location;
}];
```

Updating the `taggerWordCount` function with this code tells us that we still have 12 words, and that they are spread over 2 sentences, cool!

But what about that `schemes` parameter that we used to set up the tagger and run the enumeration? That allows the tagger to provide different types of information to the enumeration, we can tell the tagger to tag as much as it can by initializing the `schemes` variable with all available schemes. The `en-GB` string, by the way, is a [BCP-47][BP] code. The list of available schemes for this language is shown as a comment:

```objc
NSArray* schemes = [NSLinguisticTagger availableTagSchemesForLanguage:@"en-GB"];
NSLog(@"%@", schemes);

// 2012-04-17 13:08:16.947 wordcounters[54440:707] (
//    TokenType,
//    Language,
//    Script
// )
```

According to Apple's docs there are several different schemes available. One warning: if you use BCP-47 codes with more information in (such as `en-US` or `pt-BR`) then you will just get the basic 3 schemes shown above, using `en` gets the full list and other languages have varying levels of support.

Let's alter the test string and see what the different `en` schemes give us. For a new test string I'm going to use [this little ditty][MC]:

```objc
NSString* coffee = @"What I want - is a proper cup ’o coffee,"
                   @" Made in a proper copper coffee pot."
                   @" Ik kan van mijn punt,"
                   @" Ach ba mhaith liom cupán caife o ó pota caife cuí."
```

The 3rd and 4th lines have been replaced with Dutch and Irish translations of the English words in order to test the language detection. Interesting to note here is the syntaxused for multi-line strings in Objective-C, and also that I've indented the following lines so that there is a space after the punctuation at the end of the preceeding line.

Let's take a look at each scheme and what it gives us in this example.

- **Token Type**
  We can tell the words apart from the whitespace and punctuation by the tag. I could see this being useful for implementing smart punctuation in a word processor (like [SmartyPants][SP]).

- **Lexical Class**
  Instead of just words this gives us nouns, adjectives, and so on; it also classifies some of the puntuation more precisely, for example `OpenQuote`. Possibly useful in a word processing application, or to provide input to a higher-level analyser.

- **Name Type**
  This attempts to detect people and place names in the text. In this example it identified "Made" as a place name, so it's probably guessing at this based on the word capitalization.

- **Name Type or Lexical Class**
  As it suggests, a combination of the previous two schemes.

- **Lemma**
  This scheme performs word stemming, returning the stemmed word in the `tag` block parameter.

- **Language**
  This supposedly analysis each sentence to try to guess which language it is written in. I found that it worked fairly poorly when the language used the same script but did OK when they were different. In the example above it guesses that all of the text is in English, but if you change the 3rd line to "Аз не мога да ми." (the same in Bulgarian) then it guesses this correctly.
  
- **Script**
  This is the script used in the token, for us it is always "Latn" for Latin, unless you make the substitution mentioned above in which case it correctly picks up "Cyrl" for the Bulgarian Cyrillic script.

## Conclusion

For a simple word count it seems that the regular expression wins out, but the linguistic tagger provides some interesting additional information. One downside to the tagger is that it doesn't seem to be extensible in any way, so you're limited to those schemes and tags that Apple ship with the OS. There is no way to, for example, use this mechanism to tag keywords and operators in a code editor, which may be useful.

The code used for this post can be found in [this gist][GG].

[PP]: /2012/04/16/how-many-words-make-a-string
[W1]: https://en.wikipedia.org/wiki/Text_normalization
[UN]: http://www.unicode.org/reports/tr15/
[BP]: http://tools.ietf.org/html/bcp47
[MC]: http://www.mudcat.org/@displaysong.cfm?SongID=1242
[SP]: http://daringfireball.net/projects/smartypants/
[GG]: https://gist.github.com/2413356

