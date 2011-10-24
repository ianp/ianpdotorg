---
author: ianp
date: '2009-05-24 22:04:57'
layout: post
slug: setting-up-a-flash-development-environment
status: publish
title: Setting up a Flash Development Environment
wordpress_id: '104'
categories:
- Flash
- Games
tags:
- AXDT
- Eclipse
- FDT
- Flash
- Games
---

I've been playing around with [PushButtonEngine][05] and Flash over the
last couple of days, and though it may be useful to document how I've
hooked this up to my existing Eclipse install. I've started off with a
fairly vanilla Eclipse Java installation, v3.4.2, with the subversion
plug-ins and Oxygen XML for eclipse. The 2 options that I've looked at
for Flash development are \* [AXDT][01] - an open source plug-in; and \*
[FDT][02] - a commercial offering from a company in Germany. I'm going
with FDT at the moment as it seems much more feature complete, it comes
with a 30-day trial period so I guess I'll have to decide if it's worth
the money after that, the biggest problems it has are a lack of
documentation and poor support from the parent company, on the upside
there seems to be a vibrant and helpful community of users. It's also
\*massively\* overpriced, but that seems to be par for the course with
small German software vendors (EJ Technologies, I'm looking at you...).
I'm using the the build files described [in a previous post][03], and
have set up [a custom schema][04] for editing PBE level files. The other
neat tool that I've found is the [Monster Debugger][06], which has a
nice logging UI and the ability to inspect objects at run-time, it can
also dynamically invoke methods, to a fairly limited extent (Flash IDE's
are about 30 years behind Lisp in this respect, even the JVM can do
limited hot code replacement). I should probably do a round up of useful
links as well, as the Flash community in general seems to be really
helpful and there's loads of good stuff out there. [01]:
http://www.axdt.org/ [02]: http://fdt.powerflasher.com/ [03]:
http://ianp.org/2009/05/ant-build-files-for-pushbuttonengine [04]:
http://ianp.org/2009/05/level-dtd-for-pushbuttonengine [05]:
http://pushbuttonengine.com/ [06]: http://www.monsterdebugger.com/
