(ns frontend.components.app
  (:require [cemerick.url :as url]
            [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer close!]]
            [clojure.set :as set]
            [clojure.string :as str]
            [datascript :as d]
            [frontend.analytics :as analytics]
            [frontend.async :refer [put!]]
            [frontend.auth :as auth]
            [frontend.components.aside :as aside]
            [frontend.components.inspector :as inspector]
            [frontend.components.key-queue :as keyq]
            [frontend.components.canvas :as canvas]
            [frontend.components.common :as common]
            [frontend.components.overlay :as overlay]
            [frontend.favicon :as favicon]
            [frontend.models.chat :as chat-model]
            [frontend.overlay :refer [overlay-visible?]]
            [frontend.state :as state]
            [frontend.utils :as utils :include-macros true]
            [frontend.utils.seq :refer [dissoc-in select-in]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [ankha.core :as ankha])
  (:require-macros [frontend.utils :refer [html]])
  (:import [goog.ui IdGenerator]))

(def tools-templates
  {:circle {:type :ellipse
            :path "M302.9,128C292,109.1,276.2,92.8,256,81.1S213.8,64,192,64l0,128 L302.9,128z"}
   :rect   {:type :rectangle
            :path "M302.9,256c10.9-18.8,17.1-40.7,17.1-64s-6.2-45.2-17.1-64L192,192 L302.9,256z"}
   :line   {:type :line
            :path "M192,320c21.8,0,43.8-5.5,64-17.2s36-28,46.9-46.8L192,192L192,320z"}
   :pen    {:type :pencil
            :path "M81.1,256c10.9,18.8,26.7,35.2,46.9,46.8s42.2,17.2,64,17.2l0-128 L81.1,256z"}
   :text   {:type :text
            :path "M81.1,128C70.2,146.8,64,168.7,64,192s6.2,45.2,17.1,64L192,192 L81.1,128z"}
   :select {:type :cursor
            :path "M192,64c-21.8,0-43.8,5.5-64,17.2s-36,28-46.9,46.8L192,192L192,64z"}})

(def keymap
  (atom nil))

(defn app* [app owner]
  (reify
    om/IDisplayName (display-name [_] "App")
    om/IRender
    (render [_]
      (if-not (:navigation-point app)
        (html [:div#app])

        (let [controls-ch (om/get-shared owner [:comms :controls])
              persist-state! #(put! controls-ch [:state-persisted])
              restore-state! #(put! controls-ch [:state-restored])
              logged-in? (get-in app state/user-path)]
          (reset! keymap {["ctrl+s"] persist-state!
                          ["ctrl+r"] restore-state!})
          (html
           [:div#app
            (om/build keyq/keyboard-handler app
                      {:opts {:keymap keymap
                              :error-ch (get-in app [:comms :errors])}})]))))))

(defn auth-link [data owner]
  (reify
    om/IRender
    (render [_]
      (let [cast! (om/get-shared owner :cast!)
            login-button-learned? (get-in data state/login-button-learned-path)]
        (html
         (if (:cust data)
           [:form {:method "post" :action "/logout" :ref "logout-form"}
            [:input {:type "hidden" :name "__anti-forgery-token" :value (utils/csrf-token)}]
            [:input {:type "hidden" :name "redirect-to" :value (-> (.-location js/window)
                                                                   (.-href)
                                                                   (url/url)
                                                                   :path)}]
            [:a.action-logout {:on-click #(.submit (om/get-node owner "logout-form"))
                               :title "Logout"}
             (common/icon :logout)]]
           [:a.action-login {:href (auth/auth-url)
                             :data-right (when-not login-button-learned? "Sign Up")
                             :title (when login-button-learned? "Log In")
                             :on-click #(do
                                          (.preventDefault %)
                                          (cast! :login-button-clicked)
                                          (cast! :track-external-link-clicked {:path (auth/auth-url)
                                                                               :event "Signup Clicked"}))}
            (common/icon :login)]))))))

(defn chat-menu-button [app owner]
  (reify
    om/IRender
    (render [_]
      (let [cast! (om/get-shared owner :cast!)
            overlay-component (get overlay-components (or (:overlay app) :info))]
        (html
          [:a.chat-menu-button {:on-click #(cast! :main-menu-opened)
                                :role "button"
                                :data-left (when-not menu-button-learned? "Open Menu")
                                :title (when menu-button-learned? "Open Menu")}
           (common/icon :chat)])))))

(defn main-actions [data owner]
  (reify
    om/IInitState
    (init-state [_] {:listener-key (.getNextUniqueId (.getInstance IdGenerator))})
    om/IDidMount
    (did-mount [_]
      (d/listen! (om/get-shared owner :db)
                 (om/get-state owner :listener-key)
                 (fn [tx-report]
                   ;; TODO: better way to check if state changed
                   (when-let [chat-datoms (seq (filter #(= :chat/body (:a %)) (:tx-data tx-report)))]
                     (om/refresh! owner)))))
    om/IWillUnmount
    (will-unmount [_]
      (d/unlisten! (om/get-shared owner :db) (om/get-state owner :listener-key)))
    om/IRender
    (render [_]
      (let [{:keys [cast! db]} (om/get-shared owner)
            aside-opened? (get-in data state/aside-menu-opened-path)
            last-read-time (get-in data (state/last-read-chat-time-path (:document/id data)))
            unread-chat-count (chat-model/compute-unread-chat-count @db last-read-time)
            unread-chat-count (if last-read-time
                                unread-chat-count
                                ;; add one for the dummy message
                                (inc unread-chat-count))
            info-button-learned? (get-in data state/info-button-learned-path)
            menu-button-learned? (get-in data state/menu-button-learned-path)
            newdoc-button-learned? (get-in data state/newdoc-button-learned-path)
            your-docs-learned? (get-in data state/your-docs-learned-path)]
        (html
         [:div.main-actions
         ; [:a.action-menu {:on-click #(cast! :main-menu-opened)
         ;                  :role "button"
         ;                  :data-right (when-not menu-button-learned? "Open Menu")
         ;                  :title (when menu-button-learned? "Open Menu")}
         ;  (common/icon :menu)]
          ; [:a.action-menu {:on-click #(cast! :aside-menu-toggled)
          ;                  :class (when-not aside-opened? "closed")
          ;                  :data-right (when-not menu-button-learned? "Open Menu")
          ;                  :title (when menu-button-learned? (if aside-opened? "Close Menu" "Open Menu"))}
          ;  (common/icon :menu)]
          (when (and (not aside-opened?) (pos? unread-chat-count))
            [:div.unseen-eids (str unread-chat-count)])
          (om/build auth-link data)
          [:a.action-newdoc {:on-click #(cast! :newdoc-button-clicked)
                             :href "/"
                             :target "_self"
                             :data-right (when-not newdoc-button-learned? "New Document")
                             :title (when newdoc-button-learned? "New Document")}
           (common/icon :newdoc)]
          [:a.action-your-docs {:on-click #(cast! :your-docs-opened)
                                :data-right (when-not your-docs-learned? "Your Docs")
                                :title (when your-docs-learned? "Your Docs")}
           (common/icon :clock)]
          [:a.action-info {:on-click #(cast! :overlay-info-toggled)
                           :class (when-not info-button-learned? "hover")
                           :data-right (when-not info-button-learned? "What is this thing?")
                           :title (when info-button-learned? "What is this thing?")}
           (common/icon :info)]])))))


(defn app* [app owner]
  (reify
    om/IRender
    (render [_]
      (let [{:keys [cast! handlers]} (om/get-shared owner)
            aside-opened? (get-in app state/aside-menu-opened-path)
            right-click-learned? (get-in app state/right-click-learned-path)]
        (html [:div.app-main
               (om/build aside/menu app)
               [:div.app-canvas {:onContextMenu (fn [e]
                                                 (.preventDefault e)
                                                 (.stopPropagation e))}
                (om/build canvas/svg-canvas app)
                (om/build chat-menu-button app)
                ; (om/build main-actions (select-in app [state/aside-menu-opened-path
                ;                                        state/menu-button-learned-path
                ;                                        state/info-button-learned-path
                ;                                        state/newdoc-button-learned-path
                ;                                        state/login-button-learned-path
                ;                                        [:cust]
                ;                                        [:document/id]
                ;                                        (state/last-read-chat-time-path (:document/id app))]))
                (when (and (:mouse app) (not= :touch (:type (:mouse app))))
                  [:div.mouse-stats
                   (pr-str (select-keys (:mouse app) [:x :y :rx :ry]))])
                (when (get-in app [:menu :open?])
                  [:div.radial-menu {:style {:top  (- (get-in app [:menu :y]) 192)
                                             :left (- (get-in app [:menu :x]) 192)}}
                   [:svg {:width "384" :height "384"}
                    (for [[tool template] tools-templates]
                      [:path.radial-button {:d (:path template)
                                            :key tool
                                            :on-mouse-up #(do (cast! :tool-selected [tool]))
                                            :on-touch-end #(do (cast! :tool-selected [tool]))}])]
                   (for [[tool template] tools-templates]
                     [:div.radial-tool-type {:key tool}
                      (common/icon (:type template))
                      [:span (name tool)]])
                   [:div.radial-menu-nub]])
                (when (and (not right-click-learned?) (:mouse app))
                  [:div.radial-tip {:style {:top  (+ (get-in app [:mouse :y]) 16)
                                            :left (+ (get-in app [:mouse :x]) (if aside-opened? (- 16 256) 16) )}}
                   (if (= :touch (get-in app [:mouse :type]))
                     "Tap and hold to select tool"
                     "Try right-click")])]])))))

(defn app [app owner]
  (reify
    om/IRender
    (render [_]
      (if (:navigation-point app)
        (dom/div #js {:id "app" :className "app"}
          (when (overlay-visible? app)
            (om/build overlay/overlay app))
          (om/build app* app)
          (om/build overlay/main-menu-button (select-in app [state/overlays-path state/menu-button-learned-path]))
          (dom/div #js {:className "app-main-outline"}))

        (html [:div#app])))))
