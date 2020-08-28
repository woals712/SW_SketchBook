import Vue from 'vue'
import Vuex from 'vuex'
import * as database from 'database'

import * as actions from './actions'
import * as getters from './getters'

import CreatePersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const state = {
  menu: false,
  database,
  isLogin: false,
  isLoginError: false,
  username: null,
  wantuser: null,
  tempname: null,
}

const store = new Vuex.Store({
  state,
  actions,
  getters,
  mutations: {
    loginSuccess (state) {
      state.isLogin = true
      state.isLoginError = false
    },
    loginError (state) {
      state.isLogin = false
      state.isLoginError = true
    },
    logout (state) {
      state.isLogin = false
      state.isLoginError = false
    }
  },
  plugins: [
    CreatePersistedState()
  ]
})

export default store
