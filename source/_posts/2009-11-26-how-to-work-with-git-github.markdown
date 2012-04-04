---
author: ianp
date: '2009-11-26 15:33:36'
layout: post
slug: how-to-work-with-git-github
status: publish
title: How to Work with Git & GitHub
wordpress_id: '451'
categories:
- Programming
- Git
- Mac OS X
---

Based on [Alex’s comment][01] on the [Leiningen group][02], here’s my
shiny new setup for working with [Git][03] & [GitHub][04] on OS X, I'm
posting it here then it'll be easy for me to refer back to in future…

### Installing the Tools

1. install [MacPorts][05] if you don’t already have it;
2. get an up-to-date version of ruby and rubygems with `sudo port install rb-rubygems`;
3. install the `github` tool with `sudo port install json github`; and
4. upload your public key to your [GitHub account][06].

### Creating a New Project

Run `github create project_name` to create a public project, append `--private`
to create a private project (you will need a paid account at GitHub for
this).

### Forking

Run `github fork project_owner project_name`, for example to create a fork of the Cucumber testing
framework run `github fork aslakhellesoy cucumber`.

### Making Changes

Make your changes in a branch, this took me a little while to
get used to at first as it’s different from the normal way of working
with version control systems like CVS:

```sh
$ cd project_dir
$ git checkout -b feature_name
... edit edit edit ...
$ git commit -a -m "Witty comment."
```
From time to time push the branch to github
so others can see what I'm working on `git push origin feature_name`.
If the work is in a fork then you’ll also want to send a pull-request
back to the original project with `github pull-request project_owner feature_name`. All from the command line, neat huh? For more tricks,
you can also browse issues with `github issues {open|closed}`.

[01]: http://groups.google.com/group/leiningen/browse_thread/thread/c4688dfa32557edd?hl=en
[02]: http://groups.google.com/group/leiningen/
[03]: http://git-scm.com/
[04]: http://github.com/
[05]: http://macports.org/
[06]: http://github.com/account
