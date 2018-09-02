// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'es6-promise/auto'
import Vuex from 'vuex'
import store from './store'
import Router from 'vue-router'
import router from './router'
import App from './App.vue'
import axios from 'axios'

Vue.use(ElementUI);
Vue.use(Vuex);
Vue.use(Router);

Vue.config.productionTip = false;

axios.interceptors.request.use(
  config => {
    let token = store.state.token;
    if (token !== '') {  // 每次发送请求之前判断是否存在token，如果存在，则统一在http请求的header都加上token，不用每次请求都手动添加了
      config.headers.Authorization = token;
    }
    return config;
  },
  err => {
    return Promise.reject(err);
  });

axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // 返回 401 清除token信息并跳转到登录页面
          store.commit('logout');
          router.replace({
            path:'/login',
            // query: {redirect:router.app.$route.fullPath}
            query: {redirect:router.history.current.fullPath}
          })
      }
    }
    return Promise.reject(error.response.data)
  });
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>',
});
