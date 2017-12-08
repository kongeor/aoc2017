(ns aoc2017.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn str->int [s]
  (Integer. s))

(comment
  (->> (string/split (slurp (io/resource "day2.txt")) #"\n")
       (map #(string/split % #"\t"))
       (map #(map str->int %))
       (map (fn [seq] [(apply max seq) (apply min seq)]))
       (map (fn [[max min]] (- max min)))
       (reduce +)))

;; part 2

(defn even-div [seq]
  (->>
    (for [x seq
          y seq]
      (when (and (not= x y) (= (mod x y) 0))
        (/ x y)))
    (remove nil?)
    first))

(comment
  (->> (string/split (slurp (io/resource "day2.txt")) #"\n")
       (map #(string/split % #"\t"))
       (map #(map str->int %))
       (map even-div)
       (reduce +)))