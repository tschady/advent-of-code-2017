(ns aoc.2020.d16
  (:require [aoc.file-util :as file-util]
            [clojure.string :as str]))

(def input (file-util/read-chunks "2020/d16.txt"))

(def rules (first input))

(defn parse-tickets [tickets]
  (->> tickets
       str/split-lines
       rest
       (map #(re-seq #"\d+" %))
       (map #(map (fn [s] (Integer/parseInt s)) %))))

(defn parse-values [rules]
  (apply concat (for [rule (re-seq #"(\d+)-(\d+)" rules)
                      :let [start (Integer/parseInt (nth rule 1))
                            end   (Integer/parseInt (nth rule 2))]]
                  (range start (inc end)))))

(defn part-1 [input]
  (let [[rules _ tickets] input
        valid-values (set (parse-values rules))
        nearby-tickets (parse-tickets tickets)]
    (->> nearby-tickets
         (mapcat (partial remove valid-values))
         (reduce +))))


(def t "class: 1-3 or 5-7
row: 6-11 or 33-44
seat: 13-40 or 45-50

your ticket:
7,1,14

nearby tickets:
7,3,47
40,4,50
55,2,20
38,6,12")

(def ti (str/split t #"\n\n"))

(def t3 "nearby tickets:
7,3,47
40,4,50
55,2,20
38,6,12")
