(ns transduce.test
  (:use clojure.test)
  (:require [transduce.lazy :as tl]
            [transduce.reducers :as tr]))

(deftest map-state-test
  (doseq [map-state [tl/map-state tr/map-state]]
    (is (= [0 1/2 2/3 3/4 4/5]
           (->> (range 5)
             (map-state (fn [index x]
                           [(inc index) (/ x index)])
                         1)
             (into []))))))

(deftest mapcat-state-test
  (doseq [mapcat-state [tl/mapcat-state tr/mapcat-state]]
    (is (= (seq "abbbcccd")
           (->> [[:c \a] [:n 3] [:c \b] [:c \c] [:n 1] [:c \d]]
                (mapcat-state (fn [n [cmd arg]]
                               (case cmd
                                 :n [arg nil]
                                 :c [n (repeat n arg)]))
                             1)
                (into []))))))

;;TODO each should probably accept [f & args]
(deftest each-test
  (doseq [each [tl/each tr/each]]
    (is (= "abc"
           (with-out-str
             (->> [\a \b \c] (each print)))))))
