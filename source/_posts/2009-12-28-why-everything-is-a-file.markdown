---
author: ianp
date: '2009-12-28 17:54:29'
layout: post
slug: why-everything-is-a-file
status: publish
title: Why Everything is a File
wordpress_id: '469'
categories:
- Programming
- Unix
---

One of the [distinguishing characteristics of Unix][01] is the
philosophy that “everything is a file” (taken even further in
[Linux][02] and [Plan 9][03]). Reading the interview with [Ken Thompson][04] in [Coders at Work][05] (page 465) sheds some light on why
this is the case:

> **Seibel:** So you basically wrote an OS so you’d
> have a better environment to test your file system.
> **Thompson:** Yes. Halfway through there that I realized it was a real time-sharing
> system. I was writing the shell to drive the file system. And then I was
> writing a couple other programs that drove the file system. And right
> about there I said, “All I need is an editor and I’ve got an operating
> system.”

So Unix started life as a glorified test harness for Ken’s file system! Amusing…

[01]: http://en.wikipedia.org/wiki/Unix_architecture
[02]: http://en.wikipedia.org/wiki/Linux
[03]: http://plan9.bell-labs.com/plan9/
[04]: http://en.wikipedia.org/wiki/Ken_Thompson
[05]: http://www.codersatwork.com/
