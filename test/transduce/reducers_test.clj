(ns transduce.reducers-test
  (:use clojure.test)
  (:require [transduce.reducers :as t]))

(deftest map-state-test
  (is (= [0 1/2 2/3 3/4 4/5]
         (->> (range 5)
           (t/map-state (fn [index x]
                         [(inc index) (/ x index)])
                       1)
           (into [])))))

(deftest mapcat-state-and-each-test
  (is (= "abbbcccd"
         (with-out-str
           (->> [[:c \a] [:n 3] [:c \b] [:c \c] [:n 1] [:c \d]]
                (t/mapcat-state (fn [n [cmd arg]]
                                  (case cmd
                                    :n [arg nil]
                                    :c [n (repeat n arg)]))
                                1)
                (t/each print))))))
