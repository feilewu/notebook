import { createRouter,createWebHashHistory } from "vue-router";
import Home from "../components/Home.vue";
import Edit from "../components/Edit.vue";
import NoteContent from "../components/NoteContent.vue";
import SpaceDescribe from "../components/SpaceDescribe.vue";
import SpaceDisplay from "../components/SpaceDisplay.vue";

const routes = [
  {
    path:'/spaceDispaly',
    name: 'spaceDispaly',
    component: SpaceDisplay,
  },
  {
    path:'/space/:spaceId',
    name: 'home',
    component:Home,
    props: true,
    children: [
      {
        path: 'info',
        name: "space",
        component: SpaceDescribe,
      },
      {
        // 当 /user/:id/profile 匹配成功
        // UserProfile 将被渲染到 User 的 <router-view> 内部
        path: 'page/:noteId',
        component: NoteContent,
        props: true
      },
      {
        path: 'page/:noteId/edit',
        name: 'edit',
        component: Edit,
        props: true
      },
      {
        path: 'page/create',
        name: 'create',
        component: Edit,
        props: true
      }
    ],
  },
  {
    path:'/',
    redirect: {
      name: 'spaceDispaly'
    }
  },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

export default router