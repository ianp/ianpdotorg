---
layout: post
title: "Praxis: Winning at Bingo"
date: 2013-01-17 20:04
comments: true
categories:
- Programming
- Java
- Maths
---

Following on from my [previous post][PP] I'm attempting the next Programming Praxis [excercise][BP]: computing the probability of a winning board at Bingo tournaments of various sizes.

Probably the most common means of solving this kind of probability problem is the [Monte Carlo][MC] method, which uses randomization and statistical sampling to estimate probabilities. Luckiy for me, the problem space for Bingo is small enough that it is possible completely analyze the game and present exact numbers.

The individual functions are all pretty short so I'll walk through the maths and code at the same time.

### Laying the Foundations

Now, some of the numbers are going to get pretty big so primitive math won't cut it. I'm going to define a few helper functions to make working with arbitrary-precision maths easier. I'm also gong to specify a limited precision context to use for division operations, as I'd rather lose a small amount of precision that get an `ArithmeticException` if we hit any irrational numbers.

```java
private static final MathContext DIVISION = new MathContext(128);

static BigDecimal d(long l) {
    return new BigDecimal(l, MathContext.UNLIMITED);
}

static BigDecimal add(BigDecimal a, BigDecimal b) {
    return a.add(b, MathContext.UNLIMITED);
}

static BigDecimal sub(BigDecimal a, BigDecimal b) {
    return a.subtract(b);
}

static BigDecimal mul(BigDecimal a, BigDecimal b) {
    return a.multiply(b, MathContext.UNLIMITED);
}

static BigDecimal div(BigDecimal a, BigDecimal b) {
    return a.divide(b, DIVISION);
}

static BigDecimal pow(BigDecimal a, int b) {
    return a.pow(b, MathContext.UNLIMITED);
}
```

To show how these work let's define a factorial function, which is need in a moment anyway.

```java
static BigDecimal fact(BigDecimal n) {
    switch (n.compareTo(ONE)) {
        case -1: return ZERO;
        case  0: return ONE;
        default: return mul(n, fact(sub(n, ONE)));
    }
}
```

We're also going to need a [combination][WC] function, which will return the number of distinct combinations of *k* values drawn from a set *S*. Formally this is known as the binomial coefficient and is described by:

\begin{equation}
c(S,k) = {S! \over k!\space (S - k)!} 
\label{combine}
\end{equation}

The code that implements this is pretty much a literal translation of the equation, with a little data conversion and a shortcut for the case where we want to select no items or the entire set.

```java
static BigDecimal combinations(int S, int k) {
    if (k < 0 || k > S) { return ZERO; }
    if (k == 0 || k == S) { return ONE; }
    BigDecimal bS = d(S);
    BigDecimal bk = d(k);
    return div(fact(bS), mul(fact(bk), fact(sub(bS, bk))));
}
```

There are faster algorithms than this, but this is easy to understand and sufficient for our needs.

### Describing Bingo Mathematically

A Bingo board is comprised of a set of squares *S*, each with a number *n* chosen at random from the set *N*. Typically *S* comprises a 5x5 grid and *N* is the set of integers from 1 to 75. As an added wrinkle the centre square in the grid is a *free space* which is always assumed to be hit, this means that a 5x5 grid is actually a 24 element set.

To keep things simple I'm going to assume that we are using a typical board and number set, so I'll just use constants to hold the cardinalities (sizes) of the two sets.

```java
static final int NUMBERS = 75;
static final int SQUARES = 24;
```

The first thing we need to figure out is the cumulative probabity of winning after *n* numbers have been called (i.e. the probability of winning when the 4th number is called, when the 5th number is called, and so on). This can be found by looking at the probability of there being 4 hits and those 4 forming a Bingo, plus the probability of there being 5 hits and those 5 forming a Bingo, and so on up to the number of calls made so far. Note that we start at 4 as this is the minimum number of calls needed for a Bingo.

Assuming that we have a function *p(n)* which returns the probability of there being a Bingo when there are *n* hits, the cumulative probability of winning after *n* calls is given by:

\begin{equation}
w(n) = \sum _ {i=4}^n {c(\|S\|, i)\space c(\|N\| - \|S\|, n - i) \over c(\|N\|, n)} p(i)
\label{cumulative}
\end{equation}

Substituting in the values for the sizes of the board and number set simplifies this to:

\begin{equation}
w(n) = \sum _ {i=4}^n {c(24, i)\space c(51, n - i) \over c(75, n)} p(i)
\label{cumulative-simple}
\end{equation}

But we still need to define *p(n)*, this is where the small problem space comes in handy...

### Assemble a Brute Squad

The probability of a Bingo given *n* hits on the board is the number of possible Bingo positions divided by the total number of positions, so before we can work out the probability we need to get the number of Bingo positions for each hit count.

As I mentioned earlier will use a brute force method of calculating the probability of a Bingo given *n* hits on the board. To do this we first need to come up with a representation of a board, as it only takes a single bit to represent the state of each square and there are only 24 squares, we can just use an int for this.

Here's how we do that: number the squares from 0--23 left-to-right and top-to-bottom, then starting from the least significant bit we can use a 1 to represent a hit square and a 0 to represent an empty square. So a Bingo across the top row, with all other squares being empty is represented by `0b111110000000000000000000`; this and the remaining 11 possible Bingo positions (5 rows, 5 columns, and the 2 diagonals) can be represented like so:

```java
static final int[] BINGOS = {
    0b111110000000000000000000, 0b000001111100000000000000,
    0b000000000011110000000000, 0b000000000000001111100000,
    0b000000000000000000011111, 0b100001000010001000010000,
    0b010000100001000100001000, 0b001000010000000010000100,
    0b000100001000010001000010, 0b000010000100010000100001,
    0b100000100000000001000001, 0b000010001000000100010000
}
```

To create the array of Bingo combinations (indexed by number of hits, so a 25 element array) we will just loop through every possible board layout (i.e. the integers 0 through `0b11111111111111111111111`) and check each of them against the list of Bingo positions, if there is a match we can use the `Integer.bitCount()` method to count the number of hits on the board and then increment the counter there. The full code for this is:

```java
final long[] bingoCombinations = createBingoCombinations();

long[] createBingoCombinations() {
    long[] combinations = new long[SQUARES + 1];
    for (int i = 0; i <= 0b111111111111111111111111; ++i) {
        for (int bingo : BINGOS) {
            if ((i & bingo) == bingo) {
                combinations[Integer.bitCount(i)]++;
                break;
            }
        }
    }
    return combinations;
}
```

Given this we can now calculate the probability of a Bingo at each hit count as described above, the code for this is:

```java
final BigDecimal[] bingoProbabilities = createBingoProbabilities();

BigDecimal[] createBingoProbabilities() {
    BigDecimal[] probabilities = new BigDecimal[SQUARES + 1];
    for (int i = 0; i < 4; ++i) {
        probabilities[i] = ZERO;
    }
    for (int i = 4; i <= SQUARES; ++i) {
        probabilities[i] = div(d(bingoCombinations[i]), combinations(SQUARES, i));
    }
    return probabilities;
}
```

The last thing we need before moving on to calculate the actual chances of winning is a function to determine the probability of there being *k* hits hits on the board after *n* numbers have been called (the bit in equation \eqref{cumulative} that is being multiplied by *p(n)* and then summed). It's just a literal translation of the equation:

```java
static BigDecimal getHitProbability(int numHits, int numCalls) {
    return div(mul(combinations(24, numHits), combinations(51, numCalls - numHits)),
            combinations(75, numCalls));
}
```

### Chances of Winning?

We've now got everything we need to write the code for equation \eqref{cumulative}, given the previous function and the pre-computed array of proabilities the code to do this is simple:

```java
BigDecimal[] createBoardProbabilities() {
    BigDecimal[] probabilities = new BigDecimal[NUMBERS + 1];
    for (int n = 0; n < 4; ++n) {
        probabilities[n] = ZERO;
    }
    for (int n = 4; n <= NUMBERS; ++n) {
        BigDecimal sum = ZERO;
        for (int s = 4; s <= SQUARES; ++s) {
            sum = add(sum, mul(getHitProbability(s, n), bingoProbabilities[s]));
        }
        probabilities[n] = sum;
    }
    return probabilities;
}
```

This is fine for a single board, but what about the odds for a real game, with many players? The odds of finding a winner when there are *k* boards is given by:

\begin{equation}
m(n,k) = 1 - (1 - w(n))^k
\label{many}
\end{equation}

Armed with this we can implement a function to return the probability of a winning board for any number of boards and numbers called like so:

```java
BigDecimal getProbability(int numCalls, int numBoards) {
    if (numBoards == 1) {
        return boardProbabilities[numCalls];
    }
    return sub(ONE, pow(sub(ONE, boardProbabilities[numCalls]), numBoards));
}
```

And that's everything! All that's left to add is a main method to print out some stats and that's it.

```java
public static void main(String[] args) {
    BingoOdds bingo = new BingoOdds();
    bingo.printStatistics();
    bingo.printProbabilities(1, 10, 25, 50);
}
```

If you want to play with this some more, an interesting excercise would be to generalize this for any size of board and token (number) set. To do that you'll need to get rid of the static `BINGOS` array and calculate id dynamically, based on the size of the square, other than that and a coupl of constants that need to be replaced by variables everything else should work as is.

The full code, including the implementations of the print methods can be found on [GitHub][GH].

[PP]: /2013/01/15/programming-praxis/
[GH]: https://github.com/ianp/praxis-java/blob/master/src/main/java/org/ianp/praxis/BingoOdds.java
[BP]: http://programmingpraxis.com/2009/02/19/bingo/
[DB]: http://www.durangobill.com/BingoHowTo.html
[MC]: http://en.wikipedia.org/wiki/Monte_Carlo_method
[WC]: http://en.wikipedia.org/wiki/Combinations
[WP]: http://en.wikipedia.org/wiki/Permutations
