import Vue from 'vue'
import Router from 'vue-router'
import WalletsIndex from '@/components/wallet/WalletsIndex'
import ReceiveIndex from '@/components/receive/ReceiveIndex'
import Login from '@/components/Login'
import Home from '../components/Home'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面并不需要被访问
      redirect: '/wallet',
      children: [
        {
          path: '/wallet',
          name: 'WalletsIndex',
          component: WalletsIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/receive',
          name: 'ReceiveIndex',
          component: ReceiveIndex,
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
