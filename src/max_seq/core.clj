(ns max-seq.core
  (:require [clojure.pprint])
  (:gen-class))

(defn- longer-seq
  [s1 s2]
  (if (>= (count s1)(count s2))
    s1
    s2))

(defn- make-key
  [i j]
  (keyword (str i "-" j)))

(defn- max-seq
  [i j s1 s2 cache]
  (let [iter-key (make-key i j)]
    (if-let [cached-val (iter-key @cache)]
      cached-val
      (let [x       (first (drop i s1))
            y       (first (drop j s2))
            new-seq (if (or (nil? x) (nil? y))
                      ()
                      (longer-seq
                        (if (not= x y) () (concat [x] (max-seq (inc i) (inc j) s1 s2 cache)))
                        (longer-seq
                          (max-seq i       (inc j) s1 s2 cache)
                          (max-seq (inc i) j       s1 s2 cache))))]
        (swap! cache assoc iter-key new-seq)
        new-seq))))
        
(defn max-common
  [& args]
  (when (not= 2 (count args))
    (throw (ex-info (str "Need exactly two sequences to compare.") {:causes #{:bad-args}})))
  (let [s1 (map str (seq (first  args)))
        s2 (map str (seq (second args)))]
    (into [] ((memoize max-seq) 0 0 s1 s2 (atom {})))))

(defn -main
  "Take two strings as args."
  [& args]
  (let [comn (into [] (max-common (first args) (second args)))]
    (println (str "max-common of '" (first args) "' and '" (second args) "': " comn))))
