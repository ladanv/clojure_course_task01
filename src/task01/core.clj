(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn my-fn [data-in acc-in]
  (loop [data data-in 
         acc acc-in]
    (let [fst (first data)
          rst (rest data)]
      (if (empty? data)
        acc
        (recur rst (if (vector? fst)
                      (let [tag (first fst)
                            prop (second fst)]
                        (if (and (= tag :a) (= (:class prop) "l"))
                            (my-fn (rest fst) (conj acc (:href prop)))
                          (my-fn (rest fst) acc)))
                     acc))))))


(defn get-links []
" 1) Find all elements containing {:class \"r\"}.

Example:
[:h3 {:class \"r\"} [:a {:shape \"rect\", :class \"l\",
                         :href \"https://github.com/clojure/clojure\",
                         :onmousedown \"return rwt(this,'','','','4','AFQjCNFlSngH8Q4cB8TMqb710dD6ZkDSJg','','0CFYQFjAD','','',event)\"}
                     [:em {} \"clojure\"] \"/\" [:em {} \"clojure\"] \" · GitHub\"]]

   2) Extract href from the element :a.

The link from the example above is 'https://github.com/clojure/clojure'.

  3) Return vector of all 10 links.

Example: ['https://github.com/clojure/clojure', 'http://clojure.com/', . . .]
"
  (let [data (parse "clojure_google.html")]
    (my-fn data '())))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))


