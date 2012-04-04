---
author: ianp
date: '2004-10-06 22:10:48'
layout: post
slug: textmate-revisited
status: publish
title: TextMate Revisited
wordpress_id: '48'
categories:
- Programming
- TextMate
---

After a bit more usage, some pros and cons of
[TextMate](http://www.macromates.com). To evaluate it's features I've
been writing a bundle to work with [Mono](http://www.go-mono.org)/C\#
development. First the good things:

* **Shell Commands**
  automatically run external commands, this can be used to kick off a compile or run a program for example. These are defined in a custom format but are basically shell scripts embedded in a property list file. I feel it would be a better approach to just use the property list for metadata and have a pointer to a real shell script, but I suppose you could do that anyway;

* **Macros**
  these should be a standard feature in any application, even so, it's good to see them included;

* **Code Snippets**
  you can set up small text fragments to be inserted when you type a few characters and hit the tab key. More than this however, you can include positional parameters in your code snippets and hitting tab again will step through them. I use Eclipse as my IDE and it has a similar feature, so I'm right at home with this and it's a great time saver for lazy typists like me. The only down side is that there is no way to have custom text entered in to multiple locations. I'd really like to be able to have a code snippet like this:

```java
for (int $1 = 0; $1 < $2; ++$1) {
  // do something with myArray[$1] here...
}
```
  and have it fill in the variable name every place it appears;

* **Syntax Highlighting**
  not too many language syntaxes are provided by default, but it's easy to create you own. I had a workable C# syntax defined in about 15 minutes;

* **File Templates**
  like snippets but for entire files. They can reference any shell environment variables, as well as a few special variables that are supplied by TextMate. You can add your own variables to this list as well. I haven't used this much yet, but I expect it will be reasonably useful;

* **Bundles of Goodness**
  you can define your own 'feature bundles' grouping together the features listed above. All of the features are described in standard property list files, so you can also edit them by hand if you want to. What would be a nice addition would be a way to enable and disable entire bundles, at the moment you have to manually remove them from the search path to do this; and

* **Projects**
  this allows you to group multiple files together. A nice addition to this feature would be the ability to add custom variables to a project, maybe with some kind of dialog to accomplish this when you select 'New Project...'.

And a couple of gripes I have:

* **No Preferences Dialog**
  apparently it's a design [decision](http://macromates.com/blog/archives/2004/10/06/wheres-my-beloved-preference-window) on the part of the developers. Their preferred approach is to take all of the preferences and scatter them around the menus, maybe to make it look like there are loads of extra features or something. This sucks. Looking at the mailing list archives I'm not alone in thinking this;

* **No OSA Integration**
  although commands let you call out to AppleScript, the editor itself isn't scriptable at all. There are a few things which stand out as non-Mac-like actually. Sometimes this is for a good reason (for example, not using `NSTextView` enables it to have much better text handling than most other editors) but other times it just seems odd (for example not using Apple Script for scripting but instead including two alternative automation systems in macros and commands).
