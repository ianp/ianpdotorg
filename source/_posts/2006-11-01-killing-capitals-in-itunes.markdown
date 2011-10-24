---
author: ianp
date: '2006-11-01 17:21:17'
layout: post
slug: killing-capitals-in-itunes
status: publish
title: Killing Capitals in iTunes
wordpress_id: '74'
categories:
- Technology
tags:
- Apple
- iTunes
---

The latest version of [iTunes][IT] has made several changes to the UI
(again), most of them not for the better as can be read about
[here][ITD]. Still, at least one of the annoyances, the use of all
capitals for section headings in the source list, can be easily
corrected. The section headings are stored as localizable strings in a
resource file; so first shut down iTunes, then just open the
\`/Applications/iTunes.app/Contents/Resources/English.lproj/Localizable.strings\`
in a text editor (e.g. [TextMate][TM]) and search for the following
lines:

~~~~ {lang="INI" line="1"}
"135.011" = "LIBRARY";
"135.012" = "DEVICES";
"135.013" = "STORE";
"135.014" = "PLAYLISTS";
~~~~

these need changing to:

~~~~ {lang="INI" line="1"}
"135.011" = "Library";
"135.012" = "Devices";
"135.013" = "Store";
"135.014" = "Playlists";
~~~~

or anything else that you like. Now when you restart iTunes it should
all look better. \*Update:\* You also want to change this line:
\`"135.006" = "SHARED";\` to this: \`"135.006" = "Shared";\` [IT]:
http://www.apple.com/itunes/ [ITD]:
http://dsandler.org/wp/archives/2006/09/12/itunes-7-dissection [TM]:
http://www.macromates.com/
