(ns max-seq.core
  (:require [max-seq.lcs :as lcs])
  (:gen-class))

(defn -main
  "Take two strings as args."
  [& args]
  (if (not= 2 (count args))
    (println "Usage: lcs <string 1> <string 2>")
    (let [s1 (first  args)
          s2 (second args)]
      (println (str "LCS of '" s1 "' and '" s2 "':"))
      (println (str "explicit memoization: " (time (lcs/explicit-memo s1 s2))))
      (println (str "internally memoized : " (time (lcs/memoized s1 s2)))))))
