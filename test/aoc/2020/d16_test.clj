(ns aoc.2020.d16-test
  (:require [aoc.2020.d16 :as sut]
            [clojure.test :refer :all]))

(deftest challenges
  (is (= 25916 (sut/part-1 sut/input))))
