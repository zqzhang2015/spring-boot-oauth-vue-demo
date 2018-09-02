import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const tokenKey = 'session_access_token';

export default new Vuex.Store({
  strict: true,
  state: {
    token: window.sessionStorage.getItem(tokenKey) === null ? '' : window.sessionStorage.getItem(tokenKey)
  },
  mutations: {
    login(state, payload) {
      window.sessionStorage.setItem(tokenKey, payload);
      state.token= payload;
    },
    logout(state, payload) {
      window.sessionStorage.setItem(tokenKey, '');
      state.token = '';
    }
  }
})
