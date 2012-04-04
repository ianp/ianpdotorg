---
author: ianp
date: '2007-12-31 16:45:29'
layout: post
slug: custom-functions-in-bw-cannot-be-in-final-classes
status: publish
title: Custom Functions in BW Cannot be in Final Classes
wordpress_id: '85'
categories:
- Tibco
---

Or so it seems. According to [Tibco support](http://support.tibco.com),
when validating Java custom functions in Designer one of the things it
checks is the *access_flags* in the class file (to ensure that the
method is accessible).

Based on [JVM class file spec](http://java.sun.com/docs/books/jvms/second\_edition/html/ClassFile.doc.html)
the access flags should be either 0x21 (public) or 0x31 (public final);
both of these values are fine but the validator rejects the latter. The
good news however is that running the class in either the tester or in a
deployed process engine works fine.

**Update:** I forgot to mention
this, but the error message in the validator is "MISSING: Invalid java
custom function..message". Should help out searching via the Goog.
