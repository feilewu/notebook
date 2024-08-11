//import { catalogTreeStore } from './store'
import {createPinia} from 'pinia'
import { createApp } from 'vue'
import './style.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router/router'

import App from './App.vue'

const pinia = createPinia()

const app = createApp(App)

app.use(pinia)

app.use(ElementPlus)

app.use(router)

//const catalogTree = catalogTreeStore()

app.mount('#app')
