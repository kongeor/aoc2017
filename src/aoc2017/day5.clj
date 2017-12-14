(ns aoc2017.day5
  (:require [clojure.java.io :as io]
            [clojure.string :refer [split]]))

(defn inc-elem [coll idx mapper]
  (vec (map-indexed
         (fn [i e]
           (if (= i idx)
             (mapper e)
             e)) coll)))

(defn within-bounds? [i coll]
  (let [size (count coll)]
    (and (>= i 0) (< i size))))

(defn find-steps [coll mapper]
  (loop [c coll
         i 0
         steps 0]
    (println i steps)
    (if-not (within-bounds? i c)
      steps
      (let [jump (c i)]
        (recur (inc-elem c i (mapper jump)) (+ i jump) (inc steps))))))

(defn str->int [s]
  (Integer. s))

(comment
  (find-steps (mapv str->int (split (slurp (io/resource "day5.txt")) #"\n")) (fn [_] inc)))

(comment
  (find-steps (mapv str->int (split (slurp (io/resource "day5.txt")) #"\n")) (fn [jump]
                                                                               (if (> jump 2)
                                                                                 dec
                                                                                 inc))))
