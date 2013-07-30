(ns transduce.lazy
  "Provides lazy seq support for State Transducers")

(defn map-state
  "Like map, but threads a state through the sequence of transformations.
  For each x in coll, f is applied to [state x] and should return [state' x'].
  The first invocation of f uses init as the state."
  [f init coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (let [[state' x'] (f init (first s))]
        (cons x' (map-state f state' (next s)))))))

(defn mapcat-state
  "Like mapcat, but threads a state through the sequence of transformations.
  For each x in coll, f is applied to [state x] and should return [state' xs].
  The result is the concatenation of each returned xs."
  [f init coll]
  (apply concat (map-state f init coll)))

(defn each
  "Applies f to each item in coll, returns nil"
  [f coll]
  (doseq [x coll]
    (f x)))
