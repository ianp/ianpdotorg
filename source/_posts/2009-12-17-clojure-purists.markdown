---
author: ianp
date: '2009-12-17 14:39:25'
layout: post
slug: clojure-purists
status: publish
title: Clojure Purists?
wordpress_id: '463'
categories:
- Java
- Lisp
- Clojure
- Concurrency
- Performance
---

I’ve been following [Tim Bray][05]’s excellent [Concur.next][01] article
series covering approaches to concurrency in various languages, and
currently (no pun intended!) covering Clojure. The [latest article][02]
talks about a super efficient log parsing [implementation][03] done by
[Alex Osborne][04] an includes the following comment: \> “Lots of the
performance wins come from dipping into Java-land (AtomicLongs,
LinkedBlockingQueue), which is perfectly OK, but a Clojure purist would
probably see those occasions as maybe highlighting gaps in that
language’s coverage.” I’d take issue with that, one of the real
strengths of Clojure it that it has easy and fast access to the whole
Java ecosystem. As [Rich says][06]: \> “Clojure is designed to be a
hosted language, sharing the JVM type system, GC, threads etc. It
compiles all functions to JVM bytecode. Clojure is a great Java library
consumer, offering the dot-target-member notation for calls to Java.”
That seems pretty clear to me. I wonder if the people claiming to be
Clojure purists are all coming from a Lisp background rather than the
Java world? [01]:
http://www.tbray.org/ongoing/When/200x/2009/09/27/Concur-dot-next [02]:
http://www.tbray.org/ongoing/When/200x/2009/12/15/Osborne-WF2-Clojure
[03]: http://meshy.org/2009/12/13/widefinder-2-with-clojure.html [04]:
http://meshy.org/ [05]: http://www.tbray.org/ongoing/ [06]:
http://clojure.org/jvm\_hosted
