---
author: ianp
date: '2009-04-23 17:51:24'
layout: post
slug: guice-development-stages
status: publish
title: Guice Development Stages
wordpress_id: '98'
categories:
- Java
tags:
- Guice
- Java
---

[Guice][01] has a concept called 'develpment stages' which affect how
the library works. Guice is written with server-side applications in
mind (it is from Google, after all) so the behaviour for production mode
is to do a bunch of work up front so that it can catch errors as soon as
possible. The development mode defers work (i.e. object creation) until
the last moment; Here's how the Javadoc notes describe them: \*
\*Development\* we want fast startup times at the expense of runtime
performance and some up front error checking. \* \*Production\* we want
to catch errors as early as possible and take performance hits up front.
\* \*Tool\* we're running in a tool (an IDE plugin for example).
ignoring tool mode, this is exactly the opposite of the behaviour that
you want to see for client-side development. For client applications you
want to have an much error checking as possible during development but
for production use (i.e. use by a real user) you want to go for faster
start-up times. Note that for production use it is also advantageous to
defer error checking until later as well; after all, there is no point
in refusing to start an app because feature 'foo' isn't working, if the
user doesn't use or care about 'foo'! [01]:
http://google-guice.googlecode.com/
