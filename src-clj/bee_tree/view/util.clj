(ns bee-tree.view.util)

(defmacro when-content
  ([expr] `(html/content ~expr))
  ([expr & exprs] `(maybe-content (or ~expr ~@exprs))))

(defmacro maybe-substitute
  ([expr] `(if-let [x# ~expr] (html/substitute x#) identity))
  ([expr & exprs] `(maybe-substitute (or ~expr ~@exprs))))

(defmacro maybe-content
  ([expr] `(if-let [x# ~expr] (html/content x#) identity))
  ([expr & exprs] `(maybe-content (or ~expr ~@exprs))))