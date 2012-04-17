---
layout: post
title: "How Many Words Make a String?"
date: 2012-04-16 15:16
comments: true
categories:
- iOS
- Programming
---

A recent post on the [iOS Developer Tips][DT] blog provided a handy way to get the the word count for a string by using `NSScanner`, and asked for comments on alternative approaches. Pretty quickly there were a few different suggestions so I thought that I'd take a look at them to see how they compare. It turns out that the different approaches give pretty different results when run over the same test string! To be honest this isn't much of a surprise, but what was surprising is just how different the results were.

I tested the original scanner based approach and also the first four alternatives from the comments. For the test string I used this:

```objc
NSString* string = @"Peter  piper  picked  a  peck  of  pickled  pepper . No — really — he did!";
```

there are a couple of things to note here: some of the spaces are doubled up, the period is spaced French-style (i.e. with a space before and after) and the em-dashes have hair-space at either side of them. It's easier to see some of these features when you look at the same string in a proportional font: "Peter  piper  picked  a  peck  of  pickled  pepper . No — really — he did!"

Anyway, the various approaches gave very different word counts for that example:

{:.tabular}
|--------------------|-------|
| Method             | Count |
|--------------------|------:|
| Original Scanner   |    15 |
| Regular Expression |    12 |
| String Components  |    18 |
| Char Components    |    22 |
| Linguistic tagger  |    12 |
|--------------------|-------|

Anywhere from 12 to 22 words! Let's take a look at the different approaches in turn.

## Original Scanner

This is the original `NSScanner` based version from John's post, here's the code for it:

```objc
NSUInteger scannerWordCount(NSString* string) 
{
  NSScanner* scanner = [NSScanner scannerWithString:string];
  NSCharacterSet* ws = [NSCharacterSet whitespaceAndNewlineCharacterSet];
  NSUInteger words = 0;
  while ([scanner scanUpToCharactersFromSet:ws intoString:nil])
    ++words;
  return words;
}
```

This version correctly handles runs of whitespace, but it treats any non-space character as a valid word, so the French-spaced period get's counted, as do the two em-dashes. Note however that this version *does* correctly pick up the four hair-spaces.

## Regular Expression

This is my contribution:

```objc
NSUInteger regexWordCount(NSString* string)
{
  NSRegularExpression* regex = [NSRegularExpression regularExpressionWithPattern:@"\\w+" options:0 error:nil];
  return [regex numberOfMatchesInString:string options:0 range:NSMakeRange(0, [string length])];
}
```

Obviously this isn't production code as there is no error handling (or caching of the compiled regex, which may or may not make sense here). But I'd say that this version gives the correct result, both ignoring the French-stop and em-dashes, and handling all of the spaces correctly.

## String Components

This is by far the simplest solution, provided by Frank in the comments:

```objc
NSUInteger componentsByStringWordCount(NSString* string)
{
  return [[string componentsSeparatedByString:@" "] count];
}
```

Unfortunately it doesn't work at all for this string. Just looking at an actual space character means that the double spaces get counted twice, and the entire substring "No — really — he" gets treated as a single word!

Note though, that this approach is *really* easy to understand, and would be good if the input text had already been heavily normalized.

## Char Components

Almost the same as the previous version, except that this uses an `NSCharacterSet` instead of a string:

```objc
NSUInteger componentsByCharsWordCount(NSString* string)
{
  NSCharacterSet* ws = [NSCharacterSet whitespaceAndNewlineCharacterSet];
  return [[string componentsSeparatedByCharactersInSet:ws] count];
}
```

Compared to the previous version this one still double counts the 2-space wide spaces, but it correctly detects the hair-spaces surrounding the em-dashes. Useful I guess if your text has been partially normalized by collapsing runs of spaces.

## Linguistic Tagger

This one was interesting as it's an API that I haven't seen before:

```objc
NSUInteger taggerWordCount(NSString* string)
{
  NSArray* schemes = [NSArray arrayWithObject:NSLinguisticTagSchemeTokenType];
  NSLinguisticTagger* tagger = [[NSLinguisticTagger alloc] initWithTagSchemes:schemes
                                                                      options:0];
  [tagger setString:string];
  __block NSUInteger words = 0;
  [tagger enumerateTagsInRange:NSMakeRange(0, [string length])
                        scheme:NSLinguisticTagSchemeTokenType
                       options:0
                    usingBlock:^(NSString* tag, NSRange token, NSRange sentence, BOOL *stop) {
    if ([tag isEqualTo: NSLinguisticTagWord]) ++words;
  }];
  return words;
}
```

This code returns the correct number of words, so we have another winner here! Although the code is definitely more complicated than the regex based version above. Also, the originally posted code gave a result of 30, as it also calls the block for whitespace and punctuation, you need to use the `tag` block parameter to disambiguate these.

The linguistic tagger provides a number of advanced features which may be useful if you need more than just a simple word count though. Note, for example, the `sentence` block parameter which could be used to give a sentence count as well as a word count.

## Conclusion

For most text the simplest solution is to use a regular expression here. If your input text has already been normalized then the `componentsSeparatedByString:` based approach is probably the easiest to use. The linguistic tagger allows for more advanced analysis of the text.

**Update:** all of the code here, plus a `main` function to call it, is available as a [gist][GG].

[DT]: http://iphonedevelopertips.com/data-file-management/count-the-number-of-words-in-an-string.html
[GG]: https://gist.github.com/2401251

