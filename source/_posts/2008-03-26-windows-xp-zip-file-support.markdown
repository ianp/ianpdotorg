---
author: ianp
date: '2008-03-26 19:47:48'
layout: post
slug: windows-xp-zip-file-support
status: publish
title: Windows XP Zip File Support
wordpress_id: '87'
categories:
- Windows
---

Windows XP, which I'm forced to use at work at the moment, normally
treats Zip files as though they were folders, letting you view their
contents in explorer. Unfortunately there are a number of problems with
this feature: it can kill the performance of Java based apps which work
on the files (a Java bug), and it can mangle any files in the Zips with
long names or long paths (a Windows bug).

All is not lost however, to
disable this behaviour simple open up a command prompt and run the
following command: `regsvr32 /u %windir%\system32\zipfldr.dll` And,
should you want to restore the original settings, just run the same
command without the 'u' switch.
