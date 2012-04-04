---
author: ianp
date: '2009-04-21 14:07:50'
layout: post
slug: understanding-event-propagation-in-ardor3d
status: publish
title: Understanding Event Propagation in Ardor3D
wordpress_id: '97'
categories:
- Games
- Java
---

[Ardor3D][01] adds a mechanism for subscribing to events in the scene
graph; things like when a node is added or removed, or has one of it's
render states or it's bounding volume altered. The way notifications are
handled is a little confusing at first, as the listener interface
(`DirtyEventListener`) has a method which takes a `Spatial` as it's
first argument; hoever, the spatial is the node in the scene graph on
which the event occurred, it is _not_ the node that the event is fired
from.

By way of example: if there are 2 scene nodes, a `Spatial` named
'child' and a `Node` named 'parent', and the child is attached to the
parent via a call to `Node#attachChild(Spatial)` then the following
events will be fired (assuming that the child was not already attached
to another node):

1. from the child: `spatialDirty(child, Attached)`
2. from the parent: `spatialDirty(child, Attached)`
   if the parent was attached to another node (for example, 'grandparent') then this pattern would continue, like so:

3. from the grandparent: spatialDirty(child, Attached) also, if at any point in this one of the `spatialDirty` calls returns true, then the event is assumed to have been handled and propagation will stop at that point.

What this means in practice is that
if you need to know which node an event is being fired from you cannot
reuse listeners on multiple nodes. For detaching nodes the situation is
a little different: the event propagation starts at the parent node,
with the detached child passed as as the method argument.

Be aware that
the parent will already have been set to `null` when the event is
fired though, so if you need to be able to do something with the parent
you will need to add distinct listeners to each node of interest.

[01]: http://www.ardor3d.com/
