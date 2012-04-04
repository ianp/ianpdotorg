---
author: ianp
date: '2009-11-09 12:11:09'
layout: post
slug: strawman-arguments-and-coding-styles
status: publish
title: Strawman Arguments and Coding Styles
wordpress_id: '443'
categories:
- Java
- Programming
- Clojure
---

So there’s [this blog post][01] over on the [Best in Class][02] blog
that talks about ceremony in programming languages and compares
[Clojure][03] with [Java][04] on this basis. While I’d agree with the
basic premise of the article (that there is less ceremony in Clojure),
I'm less keen on the way it’s presented: by way of a needlessly verbose
strawman example.

To be fair the article does kind of admit that this is
what is being done, but it’s still annoying. With this in mind let’s see
how well we can do with the Java version of the code, relying on a
better coding style and a couple of freely available libraries (one of
the platforms much touted strengths).

For the original — 28 line —
version of the code I’ll refer you to the [original post][01] (but warn
you that it’s presented in that well known code storage format, PNG!).
The same code rewritten in a smarter manner, but still using only the
core Java libraries. This gets it down to 10 lines of code and also
makes the intent of the code clearer. There's still a fair amount of
ceremony about this however: the multiple imports, and all of the class
and static main method boilerplate.

```java
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Distinct {
  public static void main(String... args) {
    Set distinct = new HashSet(Arrays.asList(new String[] {
        "foo", "bar", "baz", "foo"
    }));
  }
}
```

Let's see if we can't do a little better with the addition of some open
source libraries. Enter [Google Collections][05], a really neat library
that improves the collections API from the JDK. We're now down to 7
lines of code, and 2 of those are just closing braces! In any reasonably
size program the class and main statements disappear into the noise, so
we're really saying that we have 2 import statements and a single line
of code. That's not too different from the Clojure version all things
considered.

```java
import java.util.Set;
import static com.google.common.collect.Sets.newHashSet;

class Distinct {
  public static void main(String... args) {
    Set distinct = newHashSet("foo", "bar", "baz", "foo");
  }
}
```

It's interesting to note that the second Java version weighs in at 10
lines of code, versus 8 for the equivalent clojure version; not much of
a difference really. I think that the benefits of Clojure come from it's
[functional style][06], [macro system][07], and excellent [concurrency support][08] — not from the fact that you can save a few lines of code
here and there.

[01]: http://blog.bestinclass.dk/index.php/2009/09/java-vs-clojure-lets-talk-ceremony/
[02]: http://blog.bestinclass.dk/
[03]: http://clojure.org/
[04]: http://java.sun.com/
[05]: http://code.google.com/p/google-collections/
[06]: http://clojure.org/functional_programming
[07]: http://clojure.org/macros
[08]: http://clojure.org/concurrent_programming
