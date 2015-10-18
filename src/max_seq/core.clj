(ns max-seq.core
  (:require [clojure.pprint])
  (:gen-class))

(def ^:private seq-cache (atom {}))

(def ^:private seq1 (atom []))
(def ^:private seq2 (atom []))

(defn- make-key
  [i j]
  (keyword (str i "-" j)))

(defn- longer-seq
  [s1 s2]
  (if (>= (count s1)(count s2))
    s1
    s2))

(defn- max-seq
  [i j]
  (let [this-key (make-key i j)]
    (if-let [this-seq (this-key @seq-cache)]
      this-seq
      (let [x-i   (first (drop i @seq1))
            y-j   (first (drop j @seq2))
            commn (if (or (nil? x-i) (nil? y-j))
                    ()
                    (longer-seq  
                      (if (not= x-i y-j) () (concat [x-i] (max-seq (inc i) (inc j))))
                      (longer-seq
                        (max-seq i       (inc j))
                        (max-seq (inc i) j))))]
        (swap! seq-cache assoc this-key commn)
        commn))))
        
(defn max-common
  [arg1 arg2]
  (reset! seq1 (vec arg1))
  (reset! seq2 (vec arg2))
  (into [] (max-seq 0 0)))

(defn -main
  "Take two strings as args."
  [& args]

  (when (not= 2 (count args))
    (throw (ex-info (str "Need exactly two sequences to compare.") {:causes #{:bad-args}})))

  (let [seq1 (into [] (map str (seq (first  args))))
        seq2 (into [] (map str (seq (second args))))
        comn (max-common seq1 seq2)]

    (println (str "max-common of '" seq1 "' and '" seq2 "': " comn))))
