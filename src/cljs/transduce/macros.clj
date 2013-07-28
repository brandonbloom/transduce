(ns transduce.macros)

(defmacro defcurried
  "Builds another arity of the fn that returns a fn awaiting the last param"
  [name doc args & body]
  (let [cargs (vec (butlast args))]
    `(defn ~name ~doc
       (~cargs (fn [x#] (~name ~@cargs x#)))
       (~args ~@body))))
