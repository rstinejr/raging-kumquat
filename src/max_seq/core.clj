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
  [& args]
  (when (not= 2 (count args))
    (throw (ex-info (str "Need exactly two sequences to compare.") {:causes #{:bad-args}})))

  (println "args" args)(flush)
  (let [s1 (map str (seq (first  args)))
        s2 (map str (seq (second args)))]
    (reset! seq1 s1)
    (reset! seq2 s2)
    (into [] (max-seq 0 0))))

(defn -main
  "Take two strings as args."
  [& args]
  (let [comn (into [] (max-common (first args) (second args)))]
    (println (str "max-common of '" (first args) "' and '" (second args) "': " comn))))
