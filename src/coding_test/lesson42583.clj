(ns coding-test.lesson42583
  (:require [coding-test.core :refer :all]
            [clojure.repl :refer :all]
            [clojure.tools.trace :refer :all]))

(defn bridge-after
  "return the state of bridge after 1 sec"
  [bridge weight-next max-weight]
  (if (nil? weight-next)
    (bridge-after bridge 0 max-weight)
    (let [next-weight-bridge (apply + (rest bridge))]
      (if (>= max-weight ((fnil #(+ next-weight-bridge %) 0) weight-next))
        (list (concat (rest bridge) (list weight-next)) true)
        (list (concat (rest bridge) (list 0)) false)))))

(comment
  (bridge-after '(0 0 0 7) 4 10)
  (bridge-after '(0 0 0 0) 7 10))

;input length of bridge, list of waiting cars, max-weight
(defn get-time [length-bridge max-weight waiting-car]
  (loop [bridge (repeat length-bridge 0)
         waiting-car waiting-car
         time 0]
    (if (or (seq waiting-car) (not= 0 (apply + bridge)))
      (let [[bridge-state car-entered?] (bridge-after bridge (first waiting-car) max-weight)]
        (if car-entered?
          (do (printlnv bridge-state (inc time))
              (recur bridge-state (rest waiting-car) (inc time)))
          (do (printlnv bridge-state (inc time))
              (recur bridge-state waiting-car (inc time)))))
      time)))

(comment
  (get-time 2 10 '(7 4 5 6))
  (get-time 100 100 '(10))
  (get-time 100 100 '(10 10 10 10 10 10 10 10 10 10)))

