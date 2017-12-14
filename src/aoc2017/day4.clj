(ns aoc2017.day4
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

;; part 1
(comment
  (count (filter #(let [tokens (string/split % #" ")]
                    (= (count (set tokens)) (count tokens)))
                 (string/split (slurp (io/resource "day4.txt")) #"\n"))))

;; part 2

(defn anagr? [s1 s2]
  (let [s1 (sort (seq s1))
        s2 (sort (seq s2))]
    (= s1 s2)))

#_(anagr? "abc" "cba")

(defn pairs [coll]
  (let [n (count coll)]
    (for [i (range 0 (dec n))
          j (range (inc i) n)
          :when (and (< i n) (not= i j))]
      [(coll i) (coll j)])))

(defn has-anargs [coll]
  (some #(apply anagr? %) (pairs coll)))

(has-anargs ["abc" "abd" "acb"])

(comment
  (count (filter #(let [tokens (string/split % #" ")]
                    (not (has-anargs tokens)))
                 (string/split (slurp (io/resource "day4.txt")) #"\n"))))
