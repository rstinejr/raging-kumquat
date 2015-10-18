(ns max-seq.core
  (:gen-class))

(def common-seqs (atom {}))

(def seq1 (atom []))
(def seq2 (atom []))


(defn max-common
  [arg1 arg2]
  (reset! seq1 (vec arg1))
  (reset! seq2 (vec arg2))
  [ ])

(defn -main
  "Take two strings as args."
  [& args]
  (when (not= 2 (count args))
    (throw (ex-info (str "Need exactly two sequences to compare.") {:causes #{:bad-args}})))
  (let [seq1 (into [] (map str (seq (first  args))))
        seq2 (into [] (map str (seq (second args))))]
  (println (str "max-common of '" seq1 "' and '" seq2 "': " (max-common seq1 seq2)))))
