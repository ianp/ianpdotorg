---
author: ianp
date: '2005-11-17 15:56:55'
layout: post
slug: where-is-the-user-interface
status: publish
title: Where is the user interface?
wordpress_id: '56'
categories:
- Technology
- User Experience
- XML
---

This is related to this quote by Michael Kay on the XML-DEV mailing
list, on the subject of validation. \> There is also scope for
reasonableness checks to catch data input \> errors. But they belong as
close to the user interface level as \> possible, not at the information
management level. Which is fine as far as it goes but the use of the
term 'user interface' is misleading, I think, to most people (myself
included) this implies 'end user' but this is not always the case. If,
for example, you are writing a service (web- or otherwise) for external,
or even only internal, use, then the 'user interface' is the service
interface that you expose and it's perfectly reasonable (in fact, I'd
argue that it's pretty much essential) to validate every message that
your service receives.
