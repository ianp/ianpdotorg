---
author: ianp
date: '2006-12-19 21:40:23'
layout: post
slug: oh-such-a-good-infrastructure
status: publish
title: Oh, Such a Good Infrastructure
wordpress_id: '79'
categories:
- Java
- RvSnoop
- OSGi
- TIBCO
---

Now that [the 1.6 release][DL] of [RvSnoop][RS] is out of the door (OK,
1.6.1 'cause of a wart inthe original release), there are certain
features that I'd like to see added, and for the 2.0 release I want to
have in place a foundation that will make it easier to add these. A full
list of features can be found in the [plan file][PF] in the [RvSnoop
Subversion repository][SVN], but some of the big ones that will impact
the overall architecture of RvSnoop are... \#\#\# Pluggable Persistence
Mechanism I'm going to migrate the record ledger to being persistent,
there are a couple of options here as to how this will be handled: flat
files, a combination of flat files and [Lucene][LUC] based indexes, or
some kind of JDBC backed store. One option would be to make the
persistence mechanism plug-able, this would also ease the use of an
all-in-memory storage system, like the one that is currently used (which
is useful when you just want to use RvSnoop as a graphical replacement
for tibrvlisten). \#\#\# Able to Run Headless I'd like to add the
ability to run RvSnoop without the UI, this would be based on loading a
pre-configured project. Combining this feature with a JDBC backed store
this could be really useful for auditing and logging messages. \#\#\#
EMS Support I registered the [emssnoop.org][ES] domain at the same time
as [rvsnoop.org][RS], and there has been a project on SourceForge for a
while, even if it hasn't had anything checked in to it yet! An open
question is whether to try for generic JMS support or just work with EMS
directly. \#\#\# User Written Plug-ins It would be nice to be able to
cleanly extend RvSnoop if required, without going back and modifying the
main code base. \#\#\# So, What About 2.0? All (well, most) of these
features point to a need for an extensible plug-in system,
[Eclipse][ECL] has shown that a good way of architecting a desktop
application (or any application, for that matter) is to build it
entirely from plug-ins wrapped around a small core. So, the upshot of
this is that I'm planning on migrating to a managed runtime, probably
[OSGi][OSGI], for the 2.0 release. I'll talk about this more in another
post. In particular, what's the cost/benefit ratio of using this type of
runtime; and which runtimes are good?

[RS]: http://rvsnoop.org/
[ES]: http://emssnoop.org/
[DL]: http://downloads.sourceforge.net/rvsn00p/rvsnoop-1.6.1-bin.tgz
[PF]: http://svn.sourceforge.net/viewvc/\*checkout\*/rvsn00p/trunk/rvsn00p/doc/plans.txt
[SVN]: http://sourceforge.net/svn/?group\_id=63447
[OSGI]: http://www.osgi.org/
[ECL]: http://www.eclipse.org/
[LUC]: http://lucene.apache.org/java/
