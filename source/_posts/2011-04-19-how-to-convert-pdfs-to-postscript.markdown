---
author: ianp
date: '2011-04-19 09:43:14'
layout: post
slug: how-to-convert-pdfs-to-postscript
status: publish
title: How to Convert PDFs to Postscript
wordpress_id: '500'
categories:
- Technology
- Command-Line Tips
---

It's easy using *pdftops*, part of the [Poppler][PP] suite of programs. First make sure it's installed, or install it (using [Homebrew][GH] on OS X):

```console
$ brew update
$ brew install poppler
```

Then the command `pdftops infile.pdf outfile.ps` can be scripted as per
usual, something like this:

```console
for page in pages/*.pdf
do
  pdftops $page postscript/`basename -s .pdf $page`.ps
done
```

Recorded in the interest of helping Google organise my brain.

[PP]: http://poppler.freedesktop.org/
[GH]: http://mxcl.github.com/homebrew/

