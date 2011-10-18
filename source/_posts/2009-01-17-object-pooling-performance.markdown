---
author: admin
date: '2009-01-17 16:21:13'
layout: post
slug: object-pooling-performance
status: publish
title: Object Pooling Performance
wordpress_id: '95'
categories:
- Games
- Java
tags:
- Java
- Performance
---

This is an attempt to compare the performance of various object reuse
strategies for [JMonkeyEngine][1] (and, indirectly, [Ardor3D][2]). See
[this JME forum topic][3] for background info. Also, it's worth bearing
in mind that the main driver for this is to reduce GC pauses, not to
improve throughput (this is mentioned in the forum topic). This is
timings from my initial object pooling implementation using 1 thread
(all timings throughout this article are in milliseconds): \> 1793,
2109, 1485, 1418, 1414, 1592, 1675, 2206, 1768, 1685 An interesting
(although unrelated to the topic at hand) thing to note here is that
it's pretty easy to spot when hotspot kicked in, although the jump in
timings between runs 4 and 5 bears investigating further and the slow
run (run 7) is certainly a worry. Run 7 seems to be consistently slow
and turning on \`-verbose:gc\` doesn't reveal anything here. This is
timings from my object pooling implementation using 2 threads, note here
that this is each thread doing a fixed chunk of work, not a fixed amount
of work shared between threads (i.e. 2x number of threads = 2x amount of
work): \> 2234, 2965, 1796, 1830, 2029, 2145, 2105, 2242, 2214, 2247 So
the timings are higher although not twice as high as might be expected ,
this is probably due to that fact that my laptop has a 2-core CPU. Now
let's look at the timings from the same with 10 threads: \> 8869, 9814,
9203, 8966, 9182, 8973, 9176, 9166, 9358, 9182 Yep, about 5x the time as
the 2 thread runs. Now let's see how the existing implementation
compares to these. OK, so now we have an idea how the thread local and
pooling based implementation performs, let's have a look at the existing
implementation to give us a baseline to compare to. This is timings from
the existing implementation using 1 thread: \> 1501, 1469, 1379, 1374,
1471, 1553, 1553, 1589, 1652, 1547 Well, that's quite a bit faster for a
single thread, although not an order magnitude type difference, and it
didn't use any locking as it's running from a single thread, a more
realistic implementation would need to have the locks in place in case
future code tried to use multiple threads. Let's have a look at 2
threads now: \> 3038, 2906, 2797, 3140, 3120, 3124, 3143, 3179, 3340,
3352 Oops! This is about what we'd expect to see, a doubling of the
amount of work doubles the amount of time needed (as the quaternion
class is now a shared resource). And 10 threads: \> 15538, 16145 OK, I
got bored of waiting after 2 runs! But it's clear to see that it's much
slower. Finally, based on suggestions from vear and also looking at the
code used in the [Javolution][4] library (a set of real-time Java
classes), I decided to try a version that reduced the number of thread
local look-ups needed, this comes at the cost of not providing a single
reusable \`ObjectPool\` class, but as that class is pretty trivial
anyway it's no great loss leaving it out of the framework. With 1
thread: \> 1189, 1149, 1096, 1229, 1261, 1506, 1352, 1415, 1295, 1424
With 2 threads: \> 1644, 1582, 1799, 1640, 1584, 1587, 1766, 1658, 1624,
1806 With 10 threads: \> 6853, 7037, 7469, 7549, 7438, 7851, 7748, 7769,
7661, 7703 Wow! it's pretty clear that the pooled approach is much
faster and that the cost of performing the thread local look-up is
fairly significant. Interestingly I also tried this using raw arrays
instead of \`ArrayList\`s and it was much slower, I can only surmise
that because \`ArrayList\` is so heavily used throughout Java it gets
insanely optimised by hotspot. As a side note, here's my Java
environment:

~~~~ {lang="Bash" line="1"}
~/tmp/perftests$ uname -a
Darwin Kernel Version 9.6.0: Mon Nov 24 17:37:00 PST 2008;
root:xnu-1228.9.59~1/RELEASE_I386
~/tmp/perftests$ java -version
java version "1.6.0_07"
Java(TM) SE Runtime Environment (build 1.6.0_07-b06-153)
Java HotSpot(TM) 64-Bit Server VM (build 1.6.0_07-b06-57, mixed mode)
~~~~

And the code used for these tests is available [here][5]. I also tried
this using 1.5 with both the server and client VMs, the 1.5 server VM is
noticeably slower and the 1.5 client VM is frankly a dog, it was 5-6
times slower than the 1.6 times given here. [1]:
http://www.jmonkeyengine.com/ [2]: http://www.ardor3d.com/ [3]:
http://www.jmonkeyengine.com/jmeforum/index.php?topic=10146.0 [4]:
http://www.javolution.com/ [5]:
http://ianp.org/2009/01/jme-thread-local-performance.tgz
