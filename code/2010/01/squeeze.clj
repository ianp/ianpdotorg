(defn- squeeze-
  ([pred merge-fn coll matched]
    (lazy-seq
      (when-let [s (seq coll)]
        (let [f (first s)
              s (second s)
              rest (rest coll)]
          (if (pred f s)
            (squeeze- pred merge-fn rest (cons f matched))
            (let [next (if matched (merge-fn (reverse (cons f matched))) f)]
              (cons next (squeeze- pred merge-fn rest nil)))))))))

(defn squeeze
  "Returns a lazy sequence of the items in coll.

  Each pair of items where (pred first second) returns non-nil will
  be merged into a single item via merge-fn in the resulting sequence.
  Multiple matching pairs will be merged with a single n-arity call
  to merge-fn. It is the responsibility of the predicate to accept
  nil values gracefully.

  Example:

  => (squeeze #(and %s (re-matches #\"\\A\\s.*\" %2))
              #(apply str (concat %&))
              [\"hello\" \" world.\" \"foo\" \" bar\"])
  (\"hello world.\" \"foo bar\")"
  [pred merge-fn coll]
  (squeeze- pred merge-fn coll nil))

