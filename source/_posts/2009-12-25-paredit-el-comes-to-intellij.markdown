---
author: ianp
date: '2009-12-25 20:21:47'
layout: post
slug: paredit-el-comes-to-intellij
status: publish
title: Paredit.el Comes to IntelliJ
wordpress_id: '465'
categories:
- Lisp
- Clojure
---

I’ve been working on adding [paredit.el][01] like structural editing to
the next version of the [La Clojure][02] plugin for [Intellij IDEA][03].
IDEA already does most of the paren matching stuff (automatically
inserting a closing paren when you type an opening paren and so on). So
far I’ve got the basic barf and slurp commands working, and splicing, as
you can see in the screenshot below:

{% img /images/2009/12/idea-slurp-and-barf.png %}

The next step is
probably to make IDEA’s expand selection code be a little smarter in the
face of s-expressions. In related news: I found a good [introduction to paredit.el][04] on [SlideShare][05] which may be of interest. I’ll try
to get the guys at IntelliJ to push out a new version of the plugin
after the 9.0.1 release is out (it’s in beta now).

[01]: http://mumble.net/~campbell/emacs/paredit.el
[02]: http://plugins.intellij.net/plugin/?id=4050
[03]: http://www.jetbrains.com/idea/index.html
[04]: http://www.slideshare.net/mudphone/paredit-preso
[05]: http://www.slideshare.net/
