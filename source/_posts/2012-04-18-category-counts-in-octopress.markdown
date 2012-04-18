---
layout: post
title: "Category Counts in Octopress"
date: 2012-04-18 10:36
comments: true
categories: 
- Writing
- Command-Line Tips
---

Here's a quick shell script to get the number of posts in each category for an [Octopress][OP] blog, just cat your `source/_posts` folder through the following one-liner:

```sh
sed -n '/^---/,/^---/p' |\
grep '^- ' |\
sort |\
uniq -s 2 -c - |\
sort -n

```

Here's what each line does:

1. extracts the Yaml front-matter from each file;
2. extracts each top-level list entry, this assumes that the only top-level list is the category list, which is the default for Octopress posts;
3. sort the lines;
4. collapse identical lines, prepending a count of the number of lines collapsed; and finally
5. sort numerically.

Maybe this will be useful to somebody out there…

[OP]: http://octopress.org/

