import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Vuelidate from 'vuelidate'

import FastClick from 'fastclick'
import VueSweetalert2 from "vue-sweetalert2"
import { sync } from 'vuex-router-sync'
import InputTag from 'vue-input-tag'
import './assets/styles/index.styl'
import eventBus from './event-bus'
import realTimeClient from '@/real-time-client'
import MetroTile from 'vue-metro-tile'
import VueGridLayout from 'vue-grid-layout'
import lodash from 'lodash'
import moment from 'moment'

// Bootstrap axios
axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.response.use(
  response => response,
  (error) => {
    return Promise.reject(error)
  }
)

Object.assign(window.SwSketchbook)
FastClick.attach(document.body)

Vue.component('input-tag', InputTag)
Vue.use(Vuelidate)
Vue.use(VueSweetalert2)
Vue.use(MetroTile)
Vue.use(VueGridLayout)

Object.defineProperty(Vue.prototype, '$lodash', { value: lodash })
Object.defineProperty(Vue.prototype, '$moment', { value: moment })

Vue.config.productionTip = false
sync(store, router)

Vue.prototype.$bus = eventBus
Vue.prototype.$rt = realTimeClient

new Vue({
  router,
  store,
  ...App
  // render: h => h(App)
}).$mount('#app')
