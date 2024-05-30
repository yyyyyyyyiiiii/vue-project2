import Vue from "vue";
import HomeView from '../views/HomeView.vue'
import Index from "@/components/Index.vue";
import Login from "@/components/Login.vue";
import VueRouter from "vue-router";
import Home from "@/components/Home.vue";
import Main from "@/components/Main.vue";
import Object from "@/components/Object.vue";

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/Home2',
    name: 'Home2',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/Index',
    name:'Index',
    component: Index,
      children:[
        {
          path:'/Home',
          name:'Home',
          meta:{
            title:'首页',
          },
          component:Home
        },
        {
          path:'/Main',
          name:'Main',
          meta:{
            title:'员工',
          },
          component:Main
        },
        {
          path:'/Object',
          name:'Object',
          meta:{
            title:'物品',
          },
          component:Object
        },
      ]
  },
  {
    path:'/',
    name:'Login',
    component: Login
  },
]

const router = new VueRouter({
  mode:'history',
  routes
})

export default router
