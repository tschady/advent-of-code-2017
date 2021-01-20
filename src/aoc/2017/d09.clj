(ns aoc.2017.d09
  (:require [aoc.file-util :as file-util]))

(def input (file-util/read-file "2017/d09.txt"))


(def grammar "
S = groups
groups = group+
group = '{' '}'
garbage = '<' '>'

")
