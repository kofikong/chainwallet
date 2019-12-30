// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// import dev from '../config/dev.env'
var axios = require('axios')
axios.defaults.baseURL = process.env.API_ROOT
axios.defaults.baseURL = ''
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
// axios 默认headers : 'Content-Type': 'application/json;charset=UTF-8'
Vue.prototype.$axios = axios
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
