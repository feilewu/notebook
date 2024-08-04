import { createRouter,createWebHashHistory } from "vue-router";
import Home from "../components/Home.vue";
import Edit from "../components/Edit.vue";
import NoteContent from "../components/NoteContent.vue";


const routes = [
  {
    path:'/home',
    name: 'home',
    component:Home,
    children: [
      {
        // 当 /user/:id/profile 匹配成功
        // UserProfile 将被渲染到 User 的 <router-view> 内部
        path: 'page/:noteId',
        component: NoteContent,
        props: true
      }
    ],
  },
  {
    path:'/',
    redirect:'/home'
  },
  {
    path:'/edit/:noteId',
    name: 'edit',
    component:Edit,
    props: true
  },
  {
    path:'/create',
    name: 'create',
    component:Edit
  }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

export default router