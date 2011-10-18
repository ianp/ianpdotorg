---
author: admin
date: '2009-12-07 17:44:50'
layout: post
slug: clojure-evaluation
status: publish
title: Clojure Evaluation
wordpress_id: '459'
categories:
- Java
- Lisp
tags:
- Clojure
- Java
- Macros
---

I've been loking at the early-access version of [Manning][01]’s
forthcoming [Clojure in Action][02] book as well as some of the
[criticism][03] of it. One of the complaints is that the current drafts
describe macros as a run-time concept and that this is wrong. The
confusion arises from the fact that [Clojure][04] (and Lisps in general)
don't follow the same path from source to execution as a more
conventional programming languages like C and Java. I'll compare four
different languages to see how they differ: C, Java, a traditional Lisp
compiler, and Clojure. \#\#\# C Like Languages This is what most people
are used to, the traditional compiled language. Here, source code is
read in and then parsed (this is normally broken out into multiple
stages, e.g. a separate lexing stage, but for our purposes we can gloss
over these details). The parser emits some form of intermediate
representation, usually an abstract syntax tree (AST), this is then used
by the compiler to generate executable code. ![C Like
Languages](/images/2009/12/c-compilation-stages.png) Again, this
potentially glosses over some details: optimizers can world on the
intermediate representation for example, or the compiler could require a
separate linking stage to generate an executable. For our purposes
though this is sufficient: we go from source to AST to executable. Also,
it could be argued that the C pre-processor operates on the source code
before the parser sees it, but in practice the C macros system is so
primitive it doesn't really warrant being called out as a separate
stage. \#\#\# Java Like Languages ![Java Like
Languages](/images/2009/12/java-compilation-stages.png) The Java like
languages differ from C in that they run on top of a virtual machine
rather than being executed directly by the OS; as a reult their compiler
emits ‘bytecode’ rather than a finished executable. This bytecode is
then executed by the virtual machine. In all modern desktop and server
virtual machines this is means just in time compiling the bytecode down
to native machine code. \#\#\# Traditional Lisps The Lisp view of things
is a little different; it's more complicated but also more powerful. The
first thing to note is that Lisp code is already basically in the form
of an AST - there is no (or not much) syntax getting in the way. Next,
there are 2 types of macros which are applied to Lisp code: macros and
reader macros. I'll duscuss them in the opposite order to the way they
are applied… ![Lisp Like
Languages](/images/2009/12/lisp-compilation-stages.png) The standard
type of Lisp macros are what most people rave about when extolling the
virtues of the language: these are chunks of code that are executed
after the source has been loaded into an AST (remember, Lisp source code
is basically in this form already, so this just involves moving from a
textual representation to something that the Lisp runtime can work
with). If a node in the AST is a macro then it is evaluated as the code
is loaded and the result of the evaluation is used to \_replace the
macro node in the AST\_. Stop and think about that for a minute - this
happens \_before\_ the code is evaluated by the regular Lisp runtime,
but yet at this point you already have access to the full Lisp
programming language. All of this means that you can do some
[pretty][05] [cool][06] [tricks][07]: how about writing your own control
constructs? Writing a DSL compiler? Logging constructs that have
\_zero\_ runtime overhead when not used (but that can be switched on and
off by users of the program, unlike \`\#define DEBUG 0\` in C)? The
second, and much less common, type of macro is the \_reader macro\_.
Reader macros operate on the character stream as it is read in, before
the AST is constructed. Basically, when the reader sees a specific
character (usually \#) it then looks at the next character and uses that
as a key into a table of functions (the read table) that tell it how to
handle future input. Using reader macros it's possible to create DSLs
that don't use s-expression syntax (s-expressions are the paren enclosed
lists that Lisp is (in)famous for); or do something as simple as
allowing the use of brackets to [write quoted lists][08] without needing
an explicit quote (i.e. writing \`[1 2 3]\` instead of \`'(1 2 3)\`).
Only once all of this has finished is a traditional lisp ready to let
it's compiler go to work turning the (now macro-free) AST into
executable code. \#\#\# Clojure Clojure is very similar to the
traditional Lisp model, with two main differences. The first difference
is the fact that, like Java, the output is bytecode which is then loaded
and executed by a standard Java virtual machine. The second difference
is that while Clojure does have [reader macros][09], the read table
isn't exposed to user programs; that is, while it operates in the same
way as a traditional Lisp there is no way for user code to alter the
behaviour of the reader. This is probably good thing as Clojure includes
a relatively large number of predefined reader macros including a
literal syntax for lists, sets, and maps, as well as lambda-expressions
(anonymous functions) and metadata. \#\#\# Summary C and Java like
languages have a huge amount of syntax baked in, but don'r provide any
way to modify this or to manipulate the program before it is compiled.
Lisp has almost no syntax but provides a mechanism for users to add
their own, and provides a mechanism to manipulate programs before they
are compiled. Clojure has some syntax (more than other Lisps, but way
less than C/Java/&c.) and provides the same mechanism for program
manipulation as other Lisps. The OmniGraffle file for the images in this
post is [available here][10] if anybody is interested. \*Update:\* this
post is intended to compare traditional C-style languages with Lisps, it
doesn't cover, for examples, so-called scripting languages such as Perl,
Python, and Ruby. [01]: http://www.manning.com/ [02]:
http://www.manning.com/rathore/ [03]:
http://www.manning-sandbox.com/thread.jspa?threadID=35150&tstart=0 [04]:
http://clojure.org/ [05]: http://weitz.de/macros.lisp [06]:
http://brierwooddesign.com/2009/2/12/fun-with-lisp-macros [07]:
http://www.paulgraham.com/avg.html [08]:
http://www.psg.com/\~dlamkins/sl/chapter03-12.html [09]:
http://clojure.org/reader\#toc1 [10]:
http://ianp.org/images/2009/12/compilation-stages.graffle.gz
