(ns max-seq.lcs
  (:require [clojure.pprint]))

(defn- longer-seq
  [s1 s2]
  (if (>= (count s1)(count s2))
    s1
    s2))

(defn- lcs
  "Recursive algorithm to find longest common sequence."
  [s1 s2 i j]
  (let [x       (first (drop i s1))
        y       (first (drop j s2))
        new-seq (if (or (nil? x) (nil? y))
                  ()
                  (longer-seq
                    (if (not= x y) () (concat [x] (lcs s1 s2 (inc i) (inc j))))
                    (longer-seq
                      (lcs s1 s2 i       (inc j))
                      (lcs s1 s2 (inc i) j     ))))]
    (println (str "lcs " i ", " j " <<<"))(flush)
    new-seq))

(defn no-memo
  "No memoization. Just wrap recursive lcs."
  [s1 s2]
  (vec (map str (lcs s1 s2 0 0))))

(defn- make-key
  [i j]
  (keyword (str i "-" j)))

(defn- max-seq
  "Use atom 'cache' for memoization."
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
        (println (str "calculated " i ", " j " <<<"))(flush)
        (swap! cache assoc iter-key new-seq)
        new-seq))))
        
        
(defn explicit-memo
  [& args]
  (when (not= 2 (count args))
    (throw (ex-info (str "Need exactly two sequences to compare.") {:causes #{:bad-args}})))
  (let [s1 (map str (seq (first  args)))
        s2 (map str (seq (second args)))]
    (into [] ((memoize max-seq) 0 0 s1 s2 (atom {})))))
