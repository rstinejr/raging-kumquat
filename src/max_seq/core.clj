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
      (println)
      (println (str "LCS of '" s1 "' and '" s2 "'"))
      (println)
      (println "No memoization:")
      (println (str (time (lcs/lcs-no-memo s1 s2))))
      (println)
      (println "Explicit memoization:")
      (println (str (time (lcs/lcs-explicit-memo s1 s2))))
      (println)
      (println "With Clojure's \"memoize\" function:")
      (println (str (time (lcs/lcs-memo s1 s2)))))))
