---
author: ianp
date: '2010-01-07 13:23:33'
layout: post
slug: prevalent-synchronicity
status: publish
title: Prevalent Synchronicity
wordpress_id: '486'
categories:
- Lisp
- Programming
- Clojure
- Databases
- Prevalent Systems
---

Maybe it’s just an idea whose time has come, but in the past few days there’ve been 2 prevalent database systems announced for [Clojure][01]: [FleetDB][02] and [Persister][03].

#### Prevalent Databases

The idea behind prevalent databases has been around for a while being, if not ‘popularised’ exactly, at least pushed by the guys behind [Prevayler][04]. Here’s how they describe them:

> Prevayler is an open source object persistence library for Java. It is an implementation of the Prevalent System design pattern, in which business objects are kept live in memory and transactions are journaled for system recovery.

#### Fleet DB

While [Mark McGranaghan][05]’s Fleet DB doesn’t use the term prevalent database, but right now that’s basically what it is. The core of Fleet DB is a Clojure based append-only log based database; it provides a native clojure query language (with built in query optimiser), schema-less records, indexes, and a server with a JSON based network protocol.

For a new new project Fleet DB also has a good set of documentation and it sounds like Mark has some big plans for it in the future. As an added benefit there are also clients for the network protocol in languages other than Clojure (Ruby officially, and a set of Python bindings in development).

#### Persister

Sergey Didenko’s [Simple Persistence for Clojure][03] project is a much less ambitious offering, but with the really cool feature of being a single (255 line, ~11KB) file that you could just drop into your project and start using - that’s pretty lightweight! From the read me file:

> Simple Persistence for Clojure is a journal-based persistence library for Clojure programs. It follows “Prevalent system” design pattern.

The intended usage is assist you in making a prevalent system. Thus you work with your in-memory data and wrap every writing call into one of `(apply-transaction*)` macros. A nice feature is that the log files are just Clojure code: transactions are logged as a valid Clojure code, so they are easy to read and run separately.

[01]: http://clojure.org/
[02]: http://fleetdb.org/
[03]: http://github.com/SergeyDidenko/Simple-Persistence-for-Clojure
[04]: http://www.prevayler.org/
[05]: http://twitter.com/mmcgrana

