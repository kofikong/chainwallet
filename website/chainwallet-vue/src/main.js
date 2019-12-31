// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
var axios = require('axios')
axios.defaults.baseURL = process.env.API_ROOT
axios.defaults.baseURL = ''
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
// axios 默认headers : 'Content-Type': 'application/json;charset=UTF-8'
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  components: { App },
  template: '<App/>'
})
