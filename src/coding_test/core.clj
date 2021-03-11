(ns coding-test.core)

(defn split [regex string]
  (clojure.string/split string regex))


;;for debugging (https://eli.thegreenplace.net/2017/notes-on-debugging-clojure-code/#id8)

(def ^:dynamic *verbose* false)

(defmacro printlnv
  [& args]
  `(when *verbose*
     (println ~@args)))

(defmacro with-verbose
  [& body]
  `(binding [*verbose* true] ~@body))

(comment
  ;which tracing tool to use for debugging

  (defn sums [n]                                            ; non-tail recursion (manual function call) -> can use clojure.tools.trace/trace-vars
    (if (= n 0)
      0
      (+ n (sums (dec n)))))

  (defn sums* [n]                                           ; tail recursion -> can't use trace-vars, use printlnv & with-verbose instead
    (loop [number n
           acc 0]
      (if (= number 0)
        acc
        (do
          (printlnv (dec number) (+ acc number))
          (recur (dec number) (+ acc number)))))))
;;---------------------------------------------------------------------------------------