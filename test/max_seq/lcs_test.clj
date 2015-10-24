(ns max-seq.lcs-test
  (:require [clojure.test :refer :all]
            [max-seq.lcs :refer [lcs-explicit-memo lcs-memo lcs-no-memo]]))

(deftest test-empties
  (testing "expect empty list"
    (is (= () (lcs-explicit-memo "" "")))
    (is (= () (lcs-explicit-memo "" "abcd")))
    (is (= () (lcs-explicit-memo "defz" "")))
    (is (= () (lcs-explicit-memo "a" "b")))))

(deftest short-strings
  (testing "expect singletons"
    (is (= ["a"] (lcs-explicit-memo "ab" "a")))
    (is (= ["b"] (lcs-explicit-memo "b"  "b")))
    (is (= ["a"] (lcs-explicit-memo "ba" "a")))
    (is (= ["c"] (lcs-explicit-memo "c" "cd")))
    (is (= ["c"] (lcs-explicit-memo "c" "dc")))))

(deftest lcs-explicit-memo-test
  (testing "non-trival seqs"
    (is (= ["a" "c" "b" "d" "a" "b" "a"] (lcs-explicit-memo "ggabgcbdabaab" "bacdbadcaabba")))))

(deftest no-memo-test
  (testing "non-trival seqs"
    (is (= ["a" "c" "b"] (lcs-no-memo "ggacb" "badcdb")))))

(deftest memoized-test
  (testing "non-trival seqs"
    (is (= ["a" "c" "b"] (lcs-memo "ggacb" "badcdb")))))
