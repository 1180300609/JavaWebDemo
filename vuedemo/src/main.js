import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from "axios";
axios.defaults.baseURL="http://127.0.0.1:8080/"
axios.interceptors.request.use(config=>{
    console.log(config)
    config.headers.token=window.sessionStorage.getItem('token')
    console.log(config)
    return config
})
Vue.prototype.$http=axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')