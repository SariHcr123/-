import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manage.vue'),
    redirect:"/home",
    children:[
      { path: 'home', name: '主页', component: () => import('../views/Home.vue')},
      { path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
      { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
      { path: 'file', name: '文件管理', component: () => import('../views/File.vue')},
      { path: 'shuju', name: '数据监测', component: () => import('../views/Shuju.vue')},
      { path: 'map', name: '电子地图', component: () => import('../views/Map.vue')},
      { path: 'yujing', name: '预警管理', component: () => import('../views/Yujing.vue')},
      { path: 'baobiao', name: '数据报表', component: () => import('../views/Baobiao.vue')},
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name:'Login',
    component:() => import('../views/Login.vue')
  },
  {
    path: '/register',
    name:'Register',
    component:() => import('../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  //设置当前路由名称
  store.commit("setPath")
  next()
})

export default router
