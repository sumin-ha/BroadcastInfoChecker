import { createApp } from 'vue'
import App from './App.vue'
import { Quasar } from 'quasar'
import quasarUserOptions from './quasar-user-options'
import { createRouter, createWebHistory } from 'vue-router'
import routes from './routes'
import { createStore } from 'vuex'
import store from './store'
import axios from 'axios'

// vue-router
const router = createRouter({
    history: createWebHistory(),
    routes
})

// vuex
const storage = createStore(store)

const app = createApp(App);
app.config.globalProperties.axios = axios;
app.use(Quasar, quasarUserOptions)
    .use(router).use(storage).mount('#app')
