(ns transduce.test
  (:require [transduce.lazy :as tl]
            [transduce.reducers :as tr]))

(doseq [map-state [tl/map-state tr/map-state]]
  (assert (= [0 2 6 12 20]
             (->> (range 5)
                  (map-state (fn [index x]
                               [(inc index) (* x index)])
                             1)
                  (into [])))))

(doseq [mapcat-state [tl/mapcat-state tr/mapcat-state]]
  (assert (= (seq "abbbcccd")
             (->> [[:c \a] [:n 3] [:c \b] [:c \c] [:n 1] [:c \d]]
                  (mapcat-state (fn [n [cmd arg]]
                                  (case cmd
                                    :n [arg nil]
                                    :c [n (repeat n arg)]))
                                1)
                  (into [])))))

(doseq [each [tl/each tr/each]]
  (assert (= "abc"
             (with-out-str
               (->> [\a \b \c] (each print))))))


