---
author: ianp
date: '2009-07-09 23:54:21'
layout: post
slug: further-discord-around-jsr-294
status: publish
title: Further discord around JSR-294
wordpress_id: '439'
categories:
- Java
tags:
- Java
- Oracle
- OSGi
- Sun
---

[Peter Kriens][01] of [OSGi][02] fame has posted some comments about the
current EDR from [JSR-294][03], the proposed Java language changes in
support of module systems: \> In Java 1..6 the language offered a pretty
pure model that was mapped to reality in the VM. With class loader
tricks we could tweak the perspective each JAR had of this pure world,
solving many real world problems. In JSR 294, we will for the first time
introduce this messy and complex runtime world in the language. Untold
millions have been spent to make Java run on hundreds of platforms, and
with one simple JSR we bring back the need for \#ifdef ... Read the
[relevant posts][05] on the mailing list, especially [this one][06]. I
generally agree with the OSGi camp here, this is a giant case of 'not
invented here' syndrome from the Sun people. It'll be interesting to see
if the acquisition by Oracle has any effect on this (or the JCP in
general) but I guess we'll only find out about that after the deal goes
through (i.e. months away yet). Hat tip to [Chris Aniszczyk][04]. [01]:
http://www.osgi.org/blog/ [02]: http://www.osgi.org/ [03]:
http://jcp.org/en/jsr/summary?id=294 [04]:
http://aniszczyk.org/2009/07/09/jsr-294-and-meta-module-systems/ [05]:
http://altair.cs.oswego.edu/pipermail/jsr294-modularity-observer/2009-July/thread.html
[06]:
http://altair.cs.oswego.edu/pipermail/jsr294-modularity-observer/2009-July/000225.html
