---
author: ianp
date: '2009-04-28 08:20:08'
layout: post
slug: embedding-clojure-part-2
status: publish
title: Embedding Clojure (part 2)
wordpress_id: '100'
categories:
- Java
- Lisp
tags:
- Clojure
- Java
- Lisp
---

Following on from the last post, it actually turns out to be much easier
to do most of the work in Clojure itself — no need for all of that
tiresome messing around with \`Var\`s and \`Symbols\`s on the Java side
of things! The trick is to define an abstract class in Java to set a few
things up and use as a hook, than implement this in Clojure. I'll go
through both sides of this, starting with the Java stuff. \#\#\# The
Abstract Class in Java Basically, I'm using the Java side of things to
set up my text pane with a standard stylesheet (I'd like it to use a
proportional font, with different colours for input, output, and error
text) and to install a key listener to send commands to the Clojure repl
whenever the user hits enter or return. The basic class then, is

~~~~ {lang="Java" line="1"}
public abstract class InteractiveConsole {
    private final Document _document = createStyledDocument():
    private final JTextPane _textpane;
    private final PipedWriter _inWriter = new PipedWriter();
    private final Reader _in;
    private final Writer _out = new PrintWriter(new DocWriter("output"), true);
    private final Writer _err = new PrintWriter(new DocWriter("error"), true);
    protected InteractiveConsole(JTextPane textpane) throws IOException {
        _textpane = textpane;
        _in = new PipedReader(_inWriter);
    }
}
~~~~

The \`createStyledDocument\` method, which I won't include here, just
sets up the style context and my colour scheme. The \`DocWriter\` class
that is references is an trivial writer subclass that just calls
\`insertString\` on the document with the named style. The other class
that I'll be wanting to use is a runnable so that I can launch the
Clojure REPL on it's own thread. It's about as trivial as it gets, it
just calls back into the two abstract methods that I'm going to provide
to provide my Clojure code somewhere to hook onto.

~~~~ {lang="Java" line="1"}
private class ConsoleRunner implements Runnable {
    private final Map<String, Object> _context;
    public ConsoleRunner(Map<String, Object> context) {
      _context = context;
    }
    @Override
    public void run() {
        for (Map.Entry<String, Object> var : _context.entrySet()) {
            bindVariable(var.getKey(), var.getValue());
        }
        doStart(_in, _out, _err);
    }
}
~~~~

With this I can provide a start method that installs my key listener and
then launches a new thread with an instance of this runnable. The two
hook methods that I'm providing are \* \`abstract void
bindVariable(String,Object)\` to allow me to set up some domain objects
on the clojure side of things; and \* \`abstract void
doStart(Reader,Writer,Writer)\` to actually start the REPL, using the
provided input, output, and error streams. \#\#\# The Clojure
Implementation Turns out to be trivial as well, the implementation of
\`bindVariable\` just interns the object passed in into the user
namespace, it's a one liner in Clojure.

~~~~ {lang="Lisp" line="1"}
(defn -bindVariable [this name value]
    (intern 'user (symbol name) value))
~~~~

The \`doStart\` method isn't much more involved either, it just sets up
the bindings and then launches the REPL.

~~~~ {lang="Lisp" line="1"}
(defn -doStart [this #^Reader in #^Writer out #^Writer err]
    (binding [*in*  (LineNumberingPushbackReader. in)
              *out* out
              *err* err]
        (clojure.main/repl)))
~~~~

Notice that here I have added type annotations so that the correct
method gets implemented, without these the Clojure code compiled but
then I got abstract method errors at runtime. Check out the docstring
for the \`repl\` function as well, there are a few useful options (for
example in my actual code I have an \`:init\` function to switch to the
user namespace, and a custom prompt). For completeness, here's the rest
of the Clojure file with the code required to inherit from the Java base
class.

~~~~ {lang="Lisp" line="1"}
(ns com.example.ClojureConsole
    (:import (clojure.lang LineNumberingPushbackReader)
             (java.io Reader Writer)
             (java.util Map))
    (:require (clojure main))
    (:gen-class
     :extends bg.beer.editor.InteractiveConsole
     :init init
     :constructors {[javax.swing.JTextPane] [String javax.swing.JTextPane]}))
(defn -init [textpane]
    [[textpane]])
~~~~

\#\#\# Summary This approach has the advantage that any additional
configuration can happen in the clojure code. It would also be easy, for
example, to have an additional script that was always run at start up,
to allow the user to customize the console further (similar to the
.emacs file in Emacs). You could also move most of the work that I'm
doing in Java into the Clojure code. I haven't done this as I may want
to support multiple languages in my console and it's nice to have a
common stylesheet and keybindings (e.g. for history) across languages.
Your mileage may vary.
