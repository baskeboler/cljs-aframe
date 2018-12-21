(ns cljs-aframe.app
  (:require [reagent.core :as r :refer [render]]
            ["aframe" :as aframe]))


(defn box []
  (let [seconds-elapsed (r/atom 0)]
    (fn []
      (js/setTimeout #(swap! seconds-elapsed inc) 10)
      [:a-box {:position "-1 0.5 -3"
               :rotation (str "0 " @seconds-elapsed " 0")
               :color "#4CC3D9"}])))

(defn sphere []
  [:a-sphere {:position "0 1.25 -5" :radius "1.25" :color "#EF2D5E"}])

(defn cylinder []
  [:a-cylinder {:position "1 0.75 -3" :radius "0.5" :height "1.5" :color "#FFC65D"}])

(defn plane []
  [:a-plane {:position "0 0 -4" :rotation "-90 0 0" :width "4" :height "4" :color "#7BC8A4"}])

(defn sky []
  [:a-sky {:color "#ECECEC"}])

(defn player []
  [:a-camera
   [:a-cursor]])

(defn scene []
  [:a-scene {:stats true}
   [box]
   [sphere]
   [cylinder]
   [plane]
   [sky]
   [player]])


(defn app []
  [:div.app
   [:h1 "hello world!"]
   [:hr]
   [scene]])

(defn mount-components []
  (render
    [app]
    (. js/document (getElementById "root"))))

(defn init! []
  (mount-components))

(init!)
