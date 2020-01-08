(ns cljs-aframe.app
  (:require [reagent.core :as r :refer [render]]
            ["aframe"]
            ["aframe-mountain-component"]
            ["aframe-sun-sky"]
            ["aframe-particle-system-component"]))

;; (comment
  ;; "    <a-scene>

      ;; <a-entity position="0 2.25 -15" particle-system="color: #000,#FFF"></a-entity>

      ;; <a-sphere position="0 1.25 -5" radius="1.25" color="#EF2D5E"></a-sphere>
      ;; <a-box position="-1 0.5 -3" rotation="0 45 0" width="1" height="1" depth="1"  color="#4CC3D9"></a-box>
      ;; <a-cylinder position="1 0.75 -3" radius="0.5" height="1.5" color="#FFC65D"></a-cylinder>
      ;; <a-plane position="0 0 -4" rotation="-90 0 0" width="4" height="4" color="#7BC8A4"></a-plane>

      ;; <a-sky color="#000000"></a-sky>

    ;; </a-scene>")

(defn stars-scene []
  [:a-scene
   [:a-entity
    {:position "0 2.25 -15"
     :particle-system "preset: rain; color: #000,#FFF"}]
   [:a-sphere
    {:position "0 1.25 -5"
     :radius "1.25"
     :color "#EF2D5E"}]
   [:a-box
    {:position "-1 0.5 -3"
     :rotation "0 45 0"
     :width 1
     :height 1
     :depth 1
     :color "#4CC3D9"}]
   [:a-cylinder
    {:position "1 0.75 -3"
     :radius 0.5
     :height 1.5
     :color "#FFC65D"}]
   [:a-plane
    {:position "0 0 -4"
     :rotation "-90 0 0"
     :width 4
     :height 4
     :color "#7BC8A4"}]
   [:a-sky
    {:color "#000000"}]])

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

(defn painting-panorama []
  [:a-sky
   {:src "/images/panorama.jpeg"}])

(defn assets []
   [:a-assets
    [:a-asset-item {:id "castle"
                    :src "/models/scene.gltf"}]])

#_(defn video []
     [:video
      {:src "http://raspberrypi:8090"
       :controls true
       :auto-play true
       :width "100%"}])

#_(defn video-sphere []
     [:a-videosphere {:src "videos/video.mp4"
                      :autoplay true
                      :loop true}])

(defn scene []
  [:a-scene {:stats true}
   [assets]
   ;; [painting-panorama]
   ;; [video-sphere]
   [box]
   ;; [:a-sun-sky {:material "side: back;"}]
   [:a-mountain {:color "#222222"}]
   [:a-gltf-model {:src "#castle"}]])
   ;; [sphere]
   ;; [cylinder]
   ;; [plane]
   ;; [sky]])
   ;; [player]])
   



(defn mount-components! []
  (render 
   [scene]
   (. js/document (getElementById "root"))))

(defn ^:export init! []
  (mount-components!))

;; (init!)

(defn ^:dev/after-load start-app []
  (println "start app")
  (mount-components!))

(defn ^:dev/before-load stop-app []
  (println "stopping app"))
