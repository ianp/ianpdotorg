---
layout: post
title: "Developing for the BlackBerry"
date: 2012-03-29 15:12
comments: true
categories: 
- Programming
- Mobile
---

For a change from the day job I've been doing some mobile development, all iOS up to now, and I've got to say it's a pretty nice development experience - especially with the new features (e.g. ARC, new literals) that are being added to Objective-C. But then earlier this week I was asked to look into writing a BlackBerry app at work, so that led me to looking into the different options that are available for that platform, here's what I looked into:

- the [BlackBerry Java SDK][BBJ];
- [Appcelerator Titanium][APT]; and
- [PhoneGap][PHG] (or [Cordova][COR] as it is becoming).

After working with the iPhone SDK all three of these options left a lot be desired! Herewith, a summary of their shortcomings...

### BlackBerry Java SDK

First off let me say that there are too many development options for the BlackBerry platform, even an Android emulation layer if you're targetting their tablet. It's a bit of a joke really.

Given their enterprise strengths, RIM should concentrate on getting one good Java based SDK and drop the Android layer. And rather than push their own WebWorks SDK they should concentrate on providing good support for Appcelerator and PhoneGap which will at least provide them with a growing stable of cross-platform apps written using these toolkits.

A final gripe: their simulator is killingly slow to launch, when running in debug mode (which is required to get full console output) it takes 5 minutes to launch on a reasonably modern Windows laptop.

### Appcelerator Titanium

I like the idea behind Titanium: native components driven by a JavaScript (or CoffeeScript!) engine, but the current implementation didn't inspire confidence. The installers for both Mac and Windows were buggy. I encountered several errors during installation and the Eclipse based IDE failed to install the BlackBerry components.

I expect that if you are just targetting iPhone and Android then Titanium is probably a viable option, although the problems that I had just getting it installed would give me pause before selecting it.

### PhoneGap / Cordova

The installation process was much smoother, and on the Mac it works with Xcode rather than installing an Eclipse based IDE. That said, the BlackBerry support again seemed to be quite poor, and only available on Windows.

One advantage of PhoneGap is that it's just HTML5, so you have access to the growing number of excellent frameworks for mobile development (e.g. [jQuery Mobile][JQM] or [Spine.mobile][SPM]) and there is also the opportunity to reuse some code between your mobile app and a web based version.

### Conclusion

For the internal app that I'm working on I'm sticking with the BB Java SDK for now, although if I were going to be doing more than a single small app I would probably invest the time to get comfortable with PhoneGap and use that (of course, at the same time I'm trying to persuade the client that iOS is a better choice).

I'd definitely use PhoneGap if I needed to write a cross-platform app as it seems to be the more mature option.

[BBJ]: http://developer.blackberry.com/java
[APT]: http://developer.appcelerator.com/
[PHG]: http://phonegap.com/
[COR]: http://incubator.apache.org/cordova/
[JQM]: http://jquerymobile.com/
[SPM]: http://spinejs.com/mobile

