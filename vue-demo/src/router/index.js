import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

Vue.use(Router);

const Welcome = () => import('@/components/Welcome.vue');
const AppLogin = () => import('@/components/AppLogin.vue');
const AppMain = () => import('@/components/AppMain.vue');
const User = () => import('@/components/User.vue');

const routes = [
  {path: '', redirect: '/index'},
  {path: '/login', component: AppLogin},
  {
    path: '/index', component: AppMain,
    children: [
      {path: '/welcome', component: Welcome},
      {path: '/user', component: User}
    ]
  },

];
const router = new Router({routes: routes});

router.beforeEach((to, from, next) => {
    let token = store.state.token;
    if (to.path === '/login') {
      if (token === '') {
        next();
      } else {
        next('/index');
      }
    } else {
      if (token === '') {
        next({
          path: '/login',
          query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
        })
      } else {
        next();
      }
    }
  }
);

export default router
