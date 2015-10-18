(ns max-seq.core
  (:require [max-seq.lcs :as lcs])
  (:gen-class))

(defn -main
  "Take two strings as args."
  [& args]
  (let [comn (lcs/max-common (first args) (second args))]
    (println (str "max-common of '" (first args) "' and '" (second args) "': " comn))))
