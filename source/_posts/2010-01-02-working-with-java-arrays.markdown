---
author: ianp
date: '2010-01-02 14:23:44'
layout: post
slug: working-with-java-arrays
status: publish
title: Working with Java Arrays
wordpress_id: '473'
categories:
- Java
- Lisp
- Clojure
- Examples
- Interop
---

One improvement that I'd like to see in Clojure is more examples in the doc strings (or maybe in a separate :example` metadata item). Still, nothing to stop me building up a set of my own. So, here are some simple examples of working with Java arrays in Clojure… Given some sample data:

```clj
(def my-list '(1 2 3 4 5))
(def my-vector [1 2 3 4 5])
(def my-map {:a "apple" :b "banana" :c "chopped liver"})
```

To convert to Java arrays:

```clj
(to-array my-list)
#
(to-array my-vector)
#
(to-array my-map)
#
```

Note that this always returns `Object[]` regardless of the contents of the collection. Note also that the map isn't flattened (the `pp` function used here is in `clojure.contrib.pprint`):

```clj
user=> (pp)
[[:a "apple"], [:b "banana"], [:c "chopped liver"]]
```

If the array is 2-dimensional there is a corresponding function:

```clj
user=> (def my-vec-2d [[1 2 3] [4 5 6] [7 8 9]])
#'user/my-vec-2d
user=> (to-array-2d my-vec-2d)
#
user=> (pp)
[[1, 2, 3], [4, 5, 6], [7, 8, 9]]
nil
```

If you need to use a specific type of array (e.g. to pass a `String[]` into a Java method) or need to use an array with more than 3 dimensions it's a little trickier:

```clj
user=> (into-array my-list)
#
user=> (pp)
[1, 2, 3, 4, 5]
nil
user=> (into-array my-vector)
#
user=> (pp)
[1, 2, 3, 4, 5]
nil
user=> (into-array my-map)
#
user=> (into-array (vals my-map))
#
user=> (pp)
["apple", "banana", "chopped liver"]
nil
```

There, that should serve as a handy reference for myself for when I’m feeling forgetful…
