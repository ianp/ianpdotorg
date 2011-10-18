---
author: admin
date: '2006-12-08 00:07:33'
layout: post
slug: factory-methods-vs-factory-classes-in-java
status: publish
title: Factory Methods vs. Factory Classes in Java
wordpress_id: '78'
categories:
- Java
- Technology
tags:
- Design Patterns
- Java
---

There are a number of cases in Java where I find myself wanting to use a
variant of the [factory method pattern][FM]. For example: \* To control
the number of objects created, or to create a limited set of objects
(one per value of an [enumeration][ENUM] for example, this could use an
backed [map][EMAP] in J2SE 5); \* To perform some checks before creating
object. Like \`init()\` called before instantiating instead of after.
Often my instinct here is to add some static methods to the class and
make the constructors private. I think that in part this comes from the
pattern name: factory \*method\*, I think a better name would have been
factory \*class\*, or maybe just factory. Anyway, doing this has some
advantages in that it reduces the number of classes that need writing
and keeps all of the code in one place. Like this: ![Factory
Method](/images/2006/12/factory-method.png) But there are drawbacks as
well. The main one being that I cannot then treat the factory like a
bean, this makes it more difficult to display in UI (by preventing the
use of common binding frameworks for example). So a better approach is
more like this: ![Factory Class](/images/2006/12/factory-class.png) Note
that this is still one class short of the pattern described in the
[Wikipedia article][FM]. The \*Creator\* interface is just unnecessary
as far as I'm concerned (although there is a case for it's existence in
scenarios like the various Java SPI-style interfaces). [FM]:
http://en.wikipedia.org/wiki/Factory\_method\_pattern [ENUM]:
http://java.sun.com/j2se/1.5.0/docs/relnotes/features.html\#enums
[EMAP]: http://java.sun.com/j2se/1.5.0/docs/api/java/util/EnumMap.html
