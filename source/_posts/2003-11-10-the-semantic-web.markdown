---
author: ianp
date: '2003-11-10 13:11:12'
layout: post
slug: the-semantic-web
status: publish
title: The Semantic Web
wordpress_id: '17'
categories:
- Technology
- Business
- Semantic Web
---

Clay Shirky's [latest
newsletter](http://www.shirky.com/writings/semantic\_syllogism.html) on
networks, economics and culture discusses the semantic web. In a
nutshell, he's not very impressed. The starting point for this argument
is that syllogisms don't model the real world well, an example: \> The
creator of [shirky.com](http://www.shirky.com) lives in Brooklyn. People
\> who live in Brooklyn speak with a Brooklyn accent. Well in this case
clearly they don't, the second assertation is clearly false, it's
obvious that not everybody who lives in Brooklyn calls birds \_boids\_,
so that absolute assertaion does not hold. The example later on using
Nike and the first amendment of the US constitution has more merit, in
this case the fact asserted (the first amendment covers the rights of US
citizens) is a subset of the actual applicability rather than a superset
as in the Brooklyn case. I think that this is still a surmountable
problem, but it may require a more complex representation of 'facts'
than initially considered. I think that maybe the way forward if not, as
Clay suggests (if only to point out it's impracticality), to attach
context to each assertation and then cross check against this. A better
approach surely would be to attach an accuracy value to each statement,
then the syllogisms could have attached accuracy values also. For
example, the following pseudo code:

~~~~ {lang="PseudoCode" line="1"}
LET clay lives in Brooklyn ACCURACY 1.0
LET people who live in brooklyn say boids ACCURACY 0.7
DEDUCE clay says boids ACCURACY 0.7
~~~~

With the final 0.7 being the product of the two 'fact' accuracies. Now
this is just something that I've come up with on the spur of the moment,
not a result of research so I don't know how generally useful this would
be, but in tools designed for human use it should be possible to
tolerate a level of inaccuracy, so long as it is marked as such. Reading
further, the examples about merging databases I can agree with the fact
that it's \_way\_ more complicated that just simply mapping field names!
When it comes to meta data, this is probably best if it can be extracted
from the data itself automatically, of course this would reduce the
amount of available data, but maybe we could tag any manually added meta
data with a date and allow it's accuracy to deteriorate as time goes by
(or as the tagged data is revised), and you could always tweak your
reasoner to reduce the accuracy of human added data anyway. Hmmm, but
this goes against what the semantic web is supposed to be for to a large
extent. Machines need to be able to rely on absolute (i.e. \`ACCURACY
1.0\`) facts to be able to use them a lot of the time. I think the
conclusion that I'm coming to is that there is a benefit to be gained
from the semantic web and it's related technologies and ideas, but
probably not as great as it's champions make out. So it's another web
related technology with potential that has been overmarketed. Nothing
new there then.
