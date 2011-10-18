---
author: admin
date: '2003-09-17 13:09:23'
layout: post
slug: binary-xml-encoding
status: publish
title: Binary XML Encoding
wordpress_id: '8'
categories:
- Technology
tags:
- XML
---

[Miguel de
Icaza](http://primates.ximian.com/\~miguel/archive/2003/Sep-16.html)
writes about binary encoding for XML (Omri's page was unavailable, so I
can't comment on that). This is already in widespread usage, a la
[WBXML](http://www.wapforum.org/what/technical.htm). It's a pretty
successful standard, given that it targets low bandwidth mobile phones
they've obviously encoded for size, a fact made easier that it also
targets a specific schema. I.e. it's not a generic XML encoding. \>
Omri's thesis is that there are multiple things that you might want to
\> optimize for: size, parsing speed and overhead for generating the
data and \> that it is not possible to define a file format that
satisfies all of those \> different needs. Well, I thought we had
already done this with XML. The fact is, you can already use your own
favourite encoding. If I want to make it easy to parse then I can use
UTF32, if I want to be 'more standard' I can use UTF8. I'm not sure
about this (I need to check the spec) but in theory you should be able
to use any encoding that you want to, as long as all parties agree on
it.
