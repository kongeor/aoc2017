(ns aoc2017.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn clean-input [input]
  (string/replace input "\n" ""))

(def data (slurp (io/resource "day1.txt")))

(defn add-first-to-end [input]
  (str input (first input)))

(defn int-val [c]
  (Character/getNumericValue c))

(defn captca [input]
  (->> (clean-input input)
       add-first-to-end
       (partition 2 1)
       (filter #(apply = %))
       (map first)
       (map int-val)
       (reduce +)))

(def sample1 "91212129")
(def sample2 "1122")

(add-first-to-end (clean-input data))
(captca data)

(def sample3 "123123\n")

(defn captca2 [data]
  (let [in (clean-input data)
        len (count in)
        hlen (/ len 2)]
    (->> (range hlen)
         (filter #(= (nth in %) (nth in (+ hlen %))))
         (map #(nth in %))
         (map int-val)
         (map (partial * 2))
         (reduce +))))

(comment
  (captca2 data))
