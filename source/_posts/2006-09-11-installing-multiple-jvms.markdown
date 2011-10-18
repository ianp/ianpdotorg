---
author: admin
date: '2006-09-11 23:19:25'
layout: post
slug: installing-multiple-jvms
status: publish
title: Installing Multiple JVMs
wordpress_id: '66'
categories:
- Java
- Technology
tags:
- Java
- User Experience
---

Some discussion in [the comments on Romain's weblog][RWC] regarding Java
applications that install private VMs. People seem to be either arguing
for or against including a VM, but there is obviously a better way. What
the installer should really do is check for a suitable JVM and if it
can't find one, offer to download it. Of course the application launcher
would also need to perform the same check in case it gets removed at a
later date. One (commercial) solution which seems to do this is
[Install4J][I4J] although I can't verify how successful the approach is
as I haven't used it personally. \*Update:\* There is a new version of
Install4J out now and the entry level price point is into 4 figures, so
I won't be using it any time soon. [RWC]:
http://jroller.com/page/gfx/?anchor=lightzone\_a\_fantastic\_java\_app
[I4J]: http://www.ej-technologies.com/products/install4j/overview.html
