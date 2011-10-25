---
author: ianp
date: '2006-05-02 19:34:56'
layout: post
slug: configuration-via-xml-or-code
status: publish
title: Configuration via XML or code?
wordpress_id: '62'
categories:
- Java
- Technology
- Frameworks
- XML
---

When is using XML based configuration files preferable to using source
based interfaces and/or classes? I've been taking a look at command
frameworks such as those that come with the various rich client toolkits
out there, and also [GUI
Commands](http://pietschy.org/software/gui-commands/). It struck me that
many things which I would allow via a concrete class or an abstract base
class these frameworks try to push out into XML files. I can see that
XML could be good for huge apps such as, for example,
[Eclipse](http://www.eclipse.org/), where the developers want to provide
extensibility without a huge class loading overhead. But most of these
rich client kits are designed to be used to create small to medium sized
applications and if this is what you are doing then I don't see the
point of scattering configuration information around the place, it's
better to keep it all together and the only place that this can happen
is right there in the source code. One final argument for keeping some
of this external is for easy internationalisation, but this can be
achieved simply using standard Java resource bundles or Eclipse style
NLS libraries (I prefer the eclipse style NLS approach as it makes code
more readable).
