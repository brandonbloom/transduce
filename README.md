**NOTE: This library is totally unrelated to Clojure 1.7's new tranducers**

# transduce

A Clojure library providing functions for threading a state accumulator through
a sequence transformation. These state/behavior pairs are sometimes called
"transducers" or "state transducers".

The class of transducers which use bounded space are particularly interesting.
They are known as [finite state transducers][1].

In particular, this library provides `map-state` and `mapcat-state`, which are
like their stateless namesakes. Also provided is a non-macro variant of
`doseq`, called `each`. Both lazy sequence and reducers versions are available
for all functions.

## Usage

```clojure
(require '[transduce.lazy :as t])
;OR: (require '[transduce.reducers :as t])

(do
  (->> [[:c \a] [:n 3] [:c \b] [:c \c] [:n 1] [:c \d]]
       (t/mapcat-state (fn [n [cmd arg]]
                         (case cmd
                           :n [arg nil]
                           :c [n (repeat n arg)]))
                     1)
       (t/each print))
  (println))
;=> abbbcccd
;=> nil
```

## Installation

Transduce artifacts are [published on Clojars][2].

To depend on this version with Lein, add the following to your `project.clj`:

```clojure
[transduce "0.1.0"]
```

## License

Copyright © 2013 Brandon Bloom

Distributed under the Eclipse Public License, the same as Clojure.

[1]: http://en.wikipedia.org/wiki/Finite_state_transducer
[2]: https://clojars.org/transduce
