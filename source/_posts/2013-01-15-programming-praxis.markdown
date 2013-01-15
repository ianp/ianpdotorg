---
layout: post
title: "Programming Praxis"
date: 2013-01-15 14:03
comments: true
categories:
- Programming
- Java
---

Over the weekend I stumbled across the [Programming Praxis][PP] web site, a blog that aims to "publishes new programming exercises weekly, at least, so that savvy programmers can maintain their skills by working the exercises and thinking outside their normal skill set". It's been running for about three years now and at the time of writing there are almost 400 problems there. I thought that I'd like to have a go at solving a few of them, I'm going to try solving them in Java initially, and then maybe revisit them in other languages to compare the solutions.

I've got solutions to the first couple of problems ready to go, and all of my solutions can be found in [this project][GH] on GitHub.

### Reverse Polish Notation Calculator

This is trivial to implement in Java using the built in `Console` class, the complete implementation (sans class boilerplace and imports) is:

```java
private static final Console console = System.console();
private static final Deque<BigDecimal> stack = new ArrayDeque<>();

private static void processLine(String line) {
    for (String s : line.split("\\s+")) {
        if ("+".equals(s)) {
            stack.push(stack.pop().add(stack.pop()));
        } else if ("-".equals(s)) {
            stack.push(stack.pop().subtract(stack.pop()));
        } else if ("*".equals(s)) {
            stack.push(stack.pop().multiply(stack.pop()));
        } else if ("/".equals(s)) {
            stack.push(stack.pop().divide(stack.pop()));
        } else if ("exit".equalsIgnoreCase(s) || "quit".equalsIgnoreCase(s)) {
            System.exit(0);
        } else {
            stack.push(new BigDecimal(s));
        }
    }
    console.format("%s%n", stack.peek()).flush();
}

public static void main(String[] args) {
    try {
        String line;
        while ((line = console.readLine("> ")) != null) {
            processLine(line);
        }
    } catch (Exception e) {
        console.format("%s: %s%n", e.getClass().getSimpleName(), e.getMessage());
    }
}
```

Compared with other solutions the only interesting things are the use of `BigDecimal` instead of primitive types, this means that the calculator supports a wider range of numbers and input formats, and the use of a `Deque` as the stack, this is a more modern class than the old Java 1.0 vintage `Stack` class.

The full class is [here][S1].

### Sieve of Eratosthenes

This classic algotrithm is a bit more interesting: my first thought was to lazily create the list of primes using modulo checks to filter out non-prime numbers. Technically this isn't the Sieve of Eratosthenes, but it's logically the same. Well, it performed terribly taking several seconds to compute the first million primes.

It turns out that one of the reasons the actual sieve is so fast is that it only uses addition rather than the more expensive modulo operations. This, plus the memory saving gained from using a `BitSet` instead of a list of `Integer`s gave me a nice, zippy, implementation. The relevant method is:

```java
public static BitSet sieve(int target) {
    BitSet primes = new BitSet(target);
    if (target < 2) { return primes; }
    primes.set(2);
    if (target < 3) { return primes; }
    for (int i = 3; i <= target; i += 2) {
        primes.set(i);
    }
    for (int prime = 3; prime * prime < target; prime = primes.nextSetBit(prime + 1)) {
        for (int i = prime + prime; i <= target; i += prime) {
            primes.clear(i);
        }
    }
    return primes;
}
```

And of course in a real implementation this would be a good candidate for memoization giving you *O(1)* performance in the common case.

The full class is [here][S2].

[PP]: http://programmingpraxis.com
[GH]: http://github.com/ianp/praxis-java
[S1]: https://github.com/ianp/praxis-java/blob/master/src/main/java/org/ianp/praxis/RPNCalculator.java
[S2]: https://github.com/ianp/praxis-java/blob/master/src/main/java/org/ianp/praxis/SieveOfEratosthenes.java

