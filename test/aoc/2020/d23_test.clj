(ns aoc.2020.d23-test
  (:require [aoc.2020.d23 :as sut]
            [clojure.test :as t :refer :all]))

(deftest challenges
  (is (= "45983627" (sut/part-1 sut/input))))
