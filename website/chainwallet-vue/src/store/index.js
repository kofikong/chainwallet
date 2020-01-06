import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
    },
    auth: {
      token: window.localStorage.getItem('token' || '') == null ? '' : window.localStorage.getItem('token' || '')
    }
  },
  mutations: {
    login (state, user) {
      state.user = user
      window.localStorage.setItem('user', JSON.stringify(user))
    },
    token (state, token) {
      state.auth = token
      window.localStorage.setItem('token', token)
    }
  }
})
