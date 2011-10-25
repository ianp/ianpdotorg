---
author: ianp
date: '2004-02-18 17:02:20'
layout: post
slug: retargetable-user-interfaces
status: publish
title: Retargetable User Interfaces
wordpress_id: '32'
categories:
- Java
- Technology
- User Experience
---

This via [Cafe au Lait](http://www.cafeaulait.org): the [Abstract User
Interface Markup Language](http://www.alphaworks.ibm.com/tech/auiml)
which, according to IBM, lets you design a UI once and then deploy it as
either a thin client via a servlet based rendering engine, or as a thick
client via a Swing based UI (why not SWT one may ask). This sounds a lot
like the functionality that Next/Apple have had for years with [Web
Objects](http://www.apple.com). Personally, I'm not too sure about the
whole idea, I think that if you're designing an essentially forms-based
application then thin clients are fine, anything else really needs a
real user interface (read: thick client). Any tool which tries to build
both from a single description is going to either create a way too
complicated and slow web experience or a dismally poor client
application.
