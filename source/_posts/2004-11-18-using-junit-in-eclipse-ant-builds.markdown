---
author: ianp
date: '2004-11-18 18:11:41'
layout: post
slug: using-junit-in-eclipse-ant-builds
status: publish
title: Using JUnit in Eclipse Ant Builds
wordpress_id: '49'
categories:
- Java
- Technology
- Ant
- Eclipse
- JUnit
- Project Automation
---

Just a quick note so that it gets into the
[Google](http://www.google.com) help engine. By default the copy of
[Ant](http://ant.apache.org) that is included with
[Eclipse](http://www.eclipse.org) does not reference it's own internal
copy of [JUnit](http://www.junit.org) on it's class path, to change this
open up the preferences window (hit command-comma if you're on a Mac)
and open the Ant/Runtime node then add the JUnit location to the global
path. If you're doing full-source editing (which is highly recommended
for all Eclipse users!) then you can just add a workspace reference and
it will work fine.
