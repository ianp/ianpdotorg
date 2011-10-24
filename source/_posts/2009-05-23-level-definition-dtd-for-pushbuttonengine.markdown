---
author: ianp
date: '2009-05-23 04:19:42'
layout: post
slug: level-definition-dtd-for-pushbuttonengine
status: publish
title: Level Definition DTD for PushButtonEngine
wordpress_id: '103'
categories:
- Flash
- Games
tags:
- Flash
- Games
- XML
---

I use Oxygen for all my XML editing and if you have a DTD or schema it
has some pretty neat autocompletion and error highlighting features,
with that in mind I've created a basic DTD for the level definition file
in [PBE][01], you can grab it from [here][02]. Note that it's not ideal,
as you get spurious warnings for the fields that you specify inside your
component definitions, but there's not much that can be done about that.
\*Update (2009-05-24):\* added an XSD [version of the file][03], this
handles unparsed content gracefully, useful for components. [01]:
http://pushbuttonengine.com/ [02]:
http://ianp.org/files/2009/05/pbelevel.dtd [03]:
http://ianp.org/files/2009/05/pbelevel.xsd
