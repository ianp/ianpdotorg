---
author: ianp
date: '2004-01-16 08:01:12'
layout: post
slug: swing-font-defaults
status: publish
title: Swing Font Defaults
wordpress_id: '26'
categories:
- Java
- User Experience
---

Fonts in Java look as ugly as sin, we all know this. Now, if you're
writing your own app or have the source code then you can get around
this by setting the rendering hints on your `JComponent`s. But if you
don't have the code, or can't alter it because, for example, it would
invalidate a support contract, is there any way of telling swing to use
antialiasing on fonts by default? I'm hoping that there is some magic
value that I can just add to my `swing.properties` file and be done
with it, but an hour of searching on the net hasn't found out what it
is.
