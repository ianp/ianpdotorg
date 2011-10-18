---
author: admin
date: '2010-03-15 18:48:51'
layout: post
slug: building-clojure-with-maven
status: publish
title: Building Clojure with Maven
wordpress_id: '495'
tags:
- Clojure
- Maven
---

Just a quick reminder on the steps I needed to take to build [Clojure][01] and install it in my local repository:

1. download the [Maven Ant tasks][02], version 2.1.0 at the time of writing;
2. move them to `$ANT_HOME/lib` (this is `/usr/share/ant/lib` on Mac OS X);
3. `cd` to my Clojure download folder, run `git pull` if needed;
3. run `ant -Dsnapshot.repo.dir=~/.m2/repository clean nightly-build`, this will perform a clean build and install it into the supplied repo; this can be anywhere, the default though is `/var/www/maven-snapshot-repository` which is probably no good;
4. to also install `clojure-contrib` just change to it's download directory and run `mvn install`.

Is simples!

[01]: http://clojure.org/
[02]: http://maven.apache.org/ant-tasks/download.html

