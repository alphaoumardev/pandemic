import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'

import './assets/style/style.css'
import './assets/boostrap/css/bootstrap.min.css'
import './assets/boostrap/css/bootstrap-icons.css'
import 'nprogress/nprogress.css'


const app = createApp(App)
installElementPlus(app)

app.use(store).use(router).mount('#app')
