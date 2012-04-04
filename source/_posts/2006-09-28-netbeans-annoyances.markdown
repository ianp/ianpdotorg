---
author: ianp
date: '2006-09-28 14:45:33'
layout: post
slug: netbeans-annoyances
status: publish
title: NetBeans Annoyances
wordpress_id: '68'
categories:
- Java
- Programming
- NetBeans
---

It's time to try a new IDE again!

Here's what sucks about netbeans this time around. I should say before I start that I'm using a beta release
(6.0 M3 2006-09-19-1800 to be specific) but anything that is obviously
new functionality I'm not going to comment on, this is all stuff that
has been around for a good long while now and so I think that these
complaints are valid. If I find anything that looks especially cool
about the new version I *will* mention it though, even if it's a bit
rough around the edges for now.

### General Points

It's still ugly as arse on the Mac. This is a problem with all Java IDEs on Mac OS X but
NetBeans has it in spades. It's far worse than Idea and Eclipse when it
comes to visual appearance. There are hardly any refactorings available
when compared to Eclipse or Idea, I mean, even basic stuff like 'inline'
is missing.

On the plus side, one of the reasons that I'm looking at
NetBeans again is to try out Jackpot, and when that's included as
standard it shoudl leapfrog the other IDEs in terms of refactoring and
code re-engineering support. Next, the support for editing keybinding is
pretty lame. For example, there appears to be no way of assigning a
keyboard shortcut that selects all text between the insertion point and
the start of the line (or end of the line for that matter).

### Find & Search

There are a number of issues with the find functionality. I've
been mainly invoking it through control + F7 (find usages), so these
comments apply specifically to that, but some of them are general to all
of the find features of the IDE.

* After a find, if I ckick refresh I have to then go through all of the dialogs again before the refresh is run. Just refresh the fucking search, OK?

* In the find results window, if the result that I am interested in is in a new file I need to selct it *twice!* The first time just opens the file but doesn't jump to the line, I have to select it again to get to the actual line.

* I always have to click through a dialog asking whether I want to search in
comments or not. I'm not against this as an option but I should be able
to select a default setting then I don't have to get this dialog every
time (maybe a 'do not show this dialog again' checkbox?).

### Conclusion

Urghh! Enough already! In their defence, the last couple of
times I've tried NetBeans it has just collapsed horribly in a steaming
pile of stack trace, so they are making improvements, but it's got a
looong way to go beforfe it's useable day-to-day.
