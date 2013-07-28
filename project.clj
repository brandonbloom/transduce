(defproject transduce "0.1.1-SNAPSHOT"
  :description "map-state, mapcat-state, etc for state transducers"
  :url "https://github.com/brandonbloom/transduce"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :source-paths ["src/clojure" "src/cljs"]
  :test-paths ["test/clojure"]
  :cljsbuild {:builds {:test {:source-paths ["src/cljs"
                                             "test/cljs"]
                              :compiler {:optimizations :advanced
                                         :output-to "out/test.js"}}}})
