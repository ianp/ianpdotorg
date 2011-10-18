---
author: admin
date: '2003-09-24 06:09:17'
layout: post
slug: auto-boxing
status: publish
title: Auto-boxing
wordpress_id: '10'
categories:
- Java
- Technology
tags:
- Java
---

I've been thinking a bit about autoboxing, a new feature slated for
[Java
1.5](http://developer.java.sun.com/developer/technicalArticles/RoadMaps/J2SE\_1.5/j2se\_1\_5.html)
which is due out at the end of the year. This is basically an automatic
conversion between primitive types (\`int\`, \`double\`, etc.) and their
object wrappers (\`Integer\`, \`Double\`, etc.). As far as I can see,
this is just a bad idea, and the only reason it's being considered is
because C\# has it. Sure, Java makes you jump though hoops when you're
using the object wrappers and there's definitely an argument for
changing \_something\_ about how this all works, but autoboxing is just
the wrong solution in every way. It hides what the language is doing for
you in a misleading way. Just one point: look at the overheads in Java
involving object creation, better than they used to be for sure, but do
we really want to add more? Surely a far better solution would be to
change the language and remove the primitive types altogether? Than it
would also make more sense to allow the standard arithmentic operators
to work with \`BigDecimal\` and \`BigInteger\` (maybe even adding a
\`Complex\` class) as well. Remember, this is just the written
expression of a program, a sufficiently smart compiler should be able to
optimize this away based on context, this was supposed to be one of the
main advantages of JIT compilation. In theory, there should be no
overhead to using the object wrappers in running code, after the first
JIT run this hypothetical code:

~~~~ {lang="Java" line="1"}
for (Integer i = new Integer(0); i < 10; ++)
    // Do something here...
~~~~

Should be no less efficient than:

~~~~ {lang="Java" line="1"}
for (int i = 0; i < 10; ++i)
    // Do something here...
~~~~

Note that we could allow the standard operators to work on these
'blessed' classes, much the same way as the addition operator works for
String concatenation today.
