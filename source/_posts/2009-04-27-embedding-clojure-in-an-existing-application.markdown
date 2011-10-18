---
author: admin
date: '2009-04-27 10:57:37'
layout: post
slug: embedding-clojure-in-an-existing-application
status: publish
title: Embedding Clojure in an Existing Application
wordpress_id: '99'
categories:
- Java
- Lisp
tags:
- Clojure
- Java
- Lisp
---

I've been taking a look at [Clojure][01] lately, as a JVM friendly
flavour of lisp I've got to say it looks pretty interesting. One problem
that I've had though is that all of the documentation out there (of
which there's very little, to be honest) seems to assume that you'll be
writing/running a pure Clojure program as you main application. There's
plenty of information about calling Java code from Clojure programs, and
\_some\_ information about extending Java classes and interfaces with
Clojure code, but nothing about getting the two talking together at
runtime. So here's how it's done. First off you need to set up the
symbols and namespaces that you are going to need to start up a clojure
environment.

~~~~ {lang="Java" line="1"}
Symbol main = Symbol.create("main");
Symbol clojureMain = Symbol.create("clojure.main");
Symbol user = Symbol.create("user");
Symbol require = Symbol.create("require");
Namespace userNS = Namespace.findOrCreate(user);
Namespace clojureMainNS = Namespace.findOrCreate(clojureMain);
~~~~

Once you have these you can \_require\_ the clojure main namespace, this
is the same one that is used to run scripts or start a REPL from the
comand line.

~~~~ {lang="Java" line="1"}
Var.intern(RT.CLOJURE_NS, require).invoke(clojureMain);
~~~~

Then, and this is the bit that I had trouble working out, you need to
bind your application's domain model (or at least those bits of it that
you want to expose) into the \_user\_ namespace in Clojure.

~~~~ {lang="Java" line="1"}
for (Map.Entry<String, Object> global : globals.entrySet()) {
    String key = global.getKey();
    Object value = global.getValue();
    Var.intern(userNS, Symbol.create(key), value);
}
~~~~

Finally you're ready to grab the \_main\_ method and run it.

~~~~ {.brush:java}
Var.intern(clojureMainNS, main).applyTo(RT.seq(new String[0]));
~~~~

The emtpy string array here is emulating an emty sargument list at the
command line. Some things to note here: you need to \_intern\_ \_vars\_
before you can use them, even for core library features like
\_require\_, this surprised me at first but when you remember that most
Lisps are built around a very small core of special forms with
everything else defined in Lisp, it makes some sense. [01]:
http://www.clojure.org/
