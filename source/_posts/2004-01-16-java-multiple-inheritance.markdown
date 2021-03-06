---
author: ianp
date: '2004-01-16 08:01:12'
layout: post
slug: java-multiple-inheritance
status: publish
title: Java Multiple Inheritance
wordpress_id: '27'
categories:
- Java
- Programming
---

Can somebody answer this question for me please: why does this code not
work?

```java
interface FooNode {
  public FooNode getSelf();
}
class BarNode {
  public BarNode getSelf() {
    return this;
  }
}
class FooBarNode extends BarNode implements FooNode {
  public FooBarNode getSelf() {
    return this;
  }
}
```

As far as I can see it should be fine, since `FooBarNode` _is a_
`FooNode` and also _is a_ `BarNode` the compiler should have
nothing to complain about. Of course it doesn't work, and I can see that
this is so from the language specification; but the question remains:
why? My complaint is really that this breaks the semantic model
completely. What, in essence, is being said by the `FooNode.getSelf()`
interface method is that it returns an object which conforms to the
`FooNode` interface. Given that `FooBarNode` does conform to this
interface it should be fine as a return type from this method. I assume
that there is some deep reason, probably to do with efficiency of the
virtual machine, why the language behaves like this; but having seen
some of the design decisions to come out of Sun's Java group I wouldn't
be too surprised if it was just an accidental fuck up.
