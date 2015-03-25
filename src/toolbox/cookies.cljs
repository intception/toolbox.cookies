(ns toolbox.cookies
  (:refer-clojure :exclude [get set! read])
  (:require [goog.net.cookies]
            [clojure.string]
            [cljs.reader :as reader]))


(defn get
  "Returns a cookie with the specified key"
  [k]
  (.decodeURIComponent js/window (.get goog.net.cookies (name k))))

(defn read
  [k]
  "Get cookie and read it with cljs.reader/read-string"
  (->> (get k)
       (#(clojure.string/replace % #"\+" " ")) ;; TODO
       reader/read-string))

(defn set!
  "Stores the cookie value using pr-str"
  [k v]
  (.set goog.net.cookies (name k) (pr-str v)))

(defn remove!
  "Removes the cookie with the specified key"
  [k]
  (.remove goog.net.cookies (name k)))
