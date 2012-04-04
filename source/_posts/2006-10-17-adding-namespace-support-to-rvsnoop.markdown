---
author: ianp
date: '2006-10-17 09:58:15'
layout: post
slug: adding-namespace-support-to-rvsnoop
status: publish
title: Adding Namespace Support to RvSnoop
wordpress_id: '72'
categories:
- Java
- RvSnoop
- Programming
- Tibco
- XML
---

For [RvSnoop][RVS] I'm currently working on adding namespaces to all of
the files that are used to save the application preferences and also the
project files. This is a Good Thing in and of itself, as it allows me to
use [XML Schema][XSD] to validate and document the files formats. But,
more importantly, it gives me the opportunity to refactor as I go alond,
the general plan is to make the projects in the next release be based on
directories rather than a single XML file, this will allow me to use a
disk based storage mechanism for messages so that they will be
persistent across sessions.

### Playing Well With Others

At the same
time, I've taken the opportunity to include some of the [Apache][ASF]
[Commons][AJC] libraries in the build. There were (well, still are) a
number of small utility classes scattered around which I'm planning on
replacing with the versions from Apache. One side effect of this is that
the build is increasing in size, to help control this I'm going to
remove the [Berkeley DB][BDB] dependency from the build. I was
origiannly planning to use this for the on disk message store but I've
changed my mind here. My current thinking is to just write the messages
out to files but to have a set of indexes (probably built using
[Lucene][ALJ]) as well for searching and sorting. There should be
another (alpha) release out later this week with the Commons and
Berkeley DB changes complete, and a release next week with the new
project structure.

[RVS]: http://rvsnoop.prg/
[XSD]: http://www.w3.org/XML/Schema
[ASF]: http://www.apache.org/
[AJC]: http://jakarta.apache.org/commons/index.html
[BDB]: http://www.oracle.com/database/berkeley-db/je/index.html
[ALJ]: http://lucene.apache.org/java/docs/index.html
