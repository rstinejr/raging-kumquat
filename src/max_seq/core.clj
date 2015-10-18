(ns max-seq.core
  (:require [max-seq.mcs :as mcs])
  (:gen-class))

(defn -main
  "Take two strings as args."
  [& args]
  (let [comn (mcs/max-common (first args) (second args))]
    (println (str "max-common of '" (first args) "' and '" (second args) "': " comn))))
