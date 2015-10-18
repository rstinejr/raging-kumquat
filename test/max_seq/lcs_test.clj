(ns max-seq.lcs-test
  (:require [clojure.test :refer :all]
            [max-seq.lcs :refer [explicit-memo memoized no-memo]]))

(deftest test-empties
  (testing "expect empty list"
    (is (= () (explicit-memo "" "")))
    (is (= () (explicit-memo "" "abcd")))
    (is (= () (explicit-memo "defz" "")))
    (is (= () (explicit-memo "a" "b")))))

(deftest short-strings
  (testing "expect singletons"
    (is (= ["a"] (explicit-memo "ab" "a")))
    (is (= ["b"] (explicit-memo "b"  "b")))
    (is (= ["a"] (explicit-memo "ba" "a")))
    (is (= ["c"] (explicit-memo "c" "cd")))
    (is (= ["c"] (explicit-memo "c" "dc")))))

(deftest explicit-memo-test
  (testing "non-trival seqs"
    (is (= ["a" "c" "b" "d" "a" "b" "a"] (explicit-memo "ggabgcbdabaab" "bacdbadcaabba")))))

(deftest no-memo-test
  (testing "non-trival seqs"
    (is (= ["a" "c" "b"] (no-memo "ggacb" "badcdb")))))

(deftest memoized-test
  (testing "non-trival seqs"
    (is (= ["a" "c" "b"] (memoized "ggacb" "badcdb")))))