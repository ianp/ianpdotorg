---
author: ianp
date: '2011-06-23 14:42:28'
layout: post
slug: handling-flag-arguments
status: publish
title: Handling Flag Arguments
wordpress_id: '506'
categories:
 - Java
 - Technology
 - API Design
 - Programming
---

[Martin Fowler][MF] has a [new bliki entry talking about flag arguments][MF2], defined as:

> A flag argument is a kind of function argument that tells the
function to carry out a different operation depending on its value.

And, as an example of this API style:

```java
Class Concert {
  public Booking book(Customer aCustomer, boolean isPremium);
}
```

And his preferred API design:

```java
Class Concert {
  public Booking bookRegular(Customer aCustomer);
  public Booking bookPremium(Customer aCustomer);
}
```

The problem with this, as Mr. Fowler points out, is that it can lead to problems with the implementation. His preferred solution is to have a private implementation method exactly like the original problematic API:

```java
private Booking bookImpl(Customer aCustomer, boolean isPremium)
```

But if we think about the problem for a little longer we can see that there is a better option available to us. The real problem with flag arguments is that they lose information at the call site, so the original example method would be called like this:

```java
. . .
myConcert.book(poorCustomer, false);
myConcert.book(richCustomer, true);
. . .
```

There's nothing to say what those true and false arguments actually mean. We can just define a type-safe enum to use instead of the boolean, that way the information is still present at the call site. This was our API becomes:

```java
Class Concert public {
  enum TicketType { REGULAR, PREMIUM }
  public Booking book(Customer aCustomer, TicketType ticketType)
```

And at the call site:

```java
. . .
myConcert.book(poorCustomer, TicketType.REGULAR);
myConcert.book(richCustomer, TicketType.PREMIUM);
. . .
```

This is easier to implement, and works for multi valued (e.g. integer) flags as well.

[MF]: http://martinfowler.com/
[MF2]: http://martinfowler.com/bliki/FlagArgument.html

