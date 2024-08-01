import { createRouter,createWebHashHistory } from "vue-router";
import Home from "../components/Home.vue";
import Edit from "../components/Edit.vue";

const routes = [
  {
    path:'/home',
    name: 'home',
    component:Home
  },
  {
    path:'/',
    redirect:'/home'
  },
  {
    path:'/edit/:id',
    name: 'edit',
    component:Edit
  }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

export default router