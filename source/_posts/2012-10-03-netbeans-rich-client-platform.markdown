---
layout: post
title: "NetBeans Rich Client Platform"
date: 2012-10-03 18:32
comments: true
categories:
- Java
- NetBeans
- User Experience
---

I'm in the process of dusting off some old Swing based apps to use more modern code, and also add some useful new features to them. At the same time I'm going to move the apps to use a more structured framework, as this should allow me to share more common code between the apps.

#### Picking a Platform

There are a number of options out there, including:

- [JSR-296][J] Swing Application Framework;
- [Eclipse RCP][E];
- [NetBeans RCP][N]; and
- [Jide][I] Desktop Application Framework.

JSR-296 is basically dead in the water at this point, and while there are a [few forks][F] doing the rounds I'm not really confident enough in any of them to want to move a reasonably sized codebase to it.

Eclipse uses a different UI toolkit altogether so it's really a nonpstarter for this excercise, although it would be a good option if starting a new project from scratch.

Jide is a swing component vendor and a relatively new entrant into the RCP space. JDAF has some things going for it: it has the best platform integration of any framework, with much better native fidelity (e.g. message dialogs look OK on Mac OS X and Gnome) than either Eclipse or NetBeans. It also has some handy built-in support for document-centric apps. The downsides are that unlike the other offerings here it's a commercial product, and it's much less ambitious in scope than either Eclipse or NetBeans, presumably as many of the other features that these offer are also Jide products (e.g. their docking framework). I also found myself fighting to work with it's limited data model support.

NetBeans seems to e a good fit for the apps that I'm converting, and it plays well with standard Swing idioms so it should be quite easy to port the code over. Recent releases have extensive support for annotation based configuration as well, which should ease the learning curve.

One downside is that most of the tutorials and documentation assume that you will also be using NetBeans as an IDE, which I won't be (I use [IntelliJ][A]), still, NetBeans RCP has pretty good Maven support so it shouldn't matter too much.

#### Setting Up a Project with NetBeans RCP

Here's how to create a project and add a module to it:

<script src="https://gist.github.com/3828460.js?file=nbproject.sh"></script>

You can then open this up in IntelliJ as a Maven based project and start editing away.

Adding additional modules (e.g. myviews, &c.) is as simple as rerunning the last command and re-importing the maven model (or enabling auto-import in IntelliJ).

[J]: http://www.jcp.org/en/jsr/detail?id=296
[F]: https://en.wikipedia.org/wiki/Swing_Application_Framework
[E]: http://wiki.eclipse.org/index.php/Rich_Client_Platform
[N]: http://netbeans.org/features/platform/
[I]: http://jidesoft.com/jdaf/
[A]: http://www.jetbrains.com/idea/
