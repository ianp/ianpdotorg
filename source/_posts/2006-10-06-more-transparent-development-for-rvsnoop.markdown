---
author: ianp
date: '2006-10-06 14:59:13'
layout: post
slug: more-transparent-development-for-rvsnoop
status: publish
title: More Transparent Development for RvSnoop
wordpress_id: '70'
categories:
- Java
- RvSnoop
- TIBCO
---

Although it's an open source project that anybody can help out with, in
practice [RvSnoop][RVS] is developed by me at present. However, a couple
of people have asked --- either in the forums or by email --- what the
current development status is. Well, in a word, it's going slower than I
had planned! However, I'll start posting to this blog a little about
what I'm working on and what my current plans are, and also try to start
making more frequent releases. I'll rearrange the distributables
slightly as well, given the speed of most connections these days I no
longer see the point of keeping a *binary only* download so I'm just
goint to include the compressed source and API docs will all of the
releases, this will be labelled as the *RvSnoop Stable* release. I'll
make more frequent releases of the code that I am working on as a
*RvSnoop Development* release. But first, a little explanation of what
you can expect from these unstable releases. I'll post later about my
plans for the upcoming releases.

### It's All Greek To Me

Projects use diffferent terminology for there release schedules, here's how I'm
using the terms.

#### Alpha

If a release is labelled *alpha* then
it means that I am still adding new features and working on major known
bugs. Once the release is feature complete and all of the major bugs
have been squashed I'll go through and clean up all of the strings,
finally I'll spend some time cleaning up any glaring UI problems. Once
this is complete I'll relabel the release *beta*.

#### Beta

Moving
forward one of the main points about the *beta* release is that it
will be considered to be a *string freeze* release. I'm aiming for
release 1.7 to be fully prepared for internationalization and hopefully
some of the people who have been using RvSnoop will help out with
translations. Also at this point the UI will be basically finished, so
the beta releases are for testing and minor bug fixing. Also during this
phase I will run all of the analysis tasks (currently only [PMD][PMD],
but more are planned) and clean up whatever they reveal.

[RVS]: http://rvsnoop.org/
[PMD]: http://pmd.sourceforge.net/
