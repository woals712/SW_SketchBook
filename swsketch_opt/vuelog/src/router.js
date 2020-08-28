import Vue from 'vue'
import Router from 'vue-router'

import IntroducePage from '@/views/IntroducePage'
import ProjectPage from '@/views/ProjectPage'
import CreateProject from '@/components/CreateProject'
import UpdateProject from '@/components/UpdateProject'
import AlgorithmPage from '@/views/AlgorithmPage'
import StudyPage from '@/views/StudyPage'
import ErrorPage from '@/views/ErrorPage'
import HomePage from '@/views/HomePage'
import LoginPage from '@/views/LoginPage'
import RegisterPage from '@/views/RegisterPage'
import CreateArticle from '@/components/CreateArticle'
import PostPage from '@/components/PostPage'
import UpdateArticle from '@/components/UpdateArticle'
import SearchArticle from '@/components/SearchArticle'
import CategoryArticle from '@/components/CategoryArticle'
import TagArticle from '@/components/TagArticle'
import UserPage from '@/views/UserPage'
import UserSearchPage from '@/views/UserSearch'
import store from './store/index'

import axios from 'axios'

Vue.use(Router)

const requireAuthGuest = () => (to, from, next) => {
  if (store.getters.menu) {
    store.dispatch('sideMenu', false)
  }

  if (store.state.isLogin && store.state.username && !store.state.tempname && !store.state.wantuser) {
    store.state.tempname = store.state.username
  }

  if (!store.state.isLogin && !store.state.wantuser) {
    Vue.swal({
      title: 'GitHub 아이디 입력',
      text: '검색하고자 하는 GitHub 아이디를 입력하세요',
      icon: 'info',
      input: "text",
      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      reverseButtons: true,
      focusCancel: true,
      cancelButtonText: `취소`,
      confirmButtonText: `입력`
    }).then((result) => {
      if (result.value) {
        Vue.swal({
          icon: "info",
          title: "열람 모드로 전환됩니다. 홈 버튼을 누를 경우 초기화 됩니다.",
        })
        store.state.wantuser = result.value
        // alert(store.state.guest)
        next()
      }
    })
  } else if (store.state.isLogin && store.state.wantuser) {
    store.state.tempname = null
    next()
  } else {
    next()
  }
}
const requireAuth = () => (to, from, next) => {
  if (store.getters.menu) {
    store.dispatch('sideMenu', false)
  }
  if (store.state.isLogin) {
    return next()
  } else {
    Vue.swal({
      icon: "error",
      title: "로그인이 필요한 작업입니다",
    })
  }
  next('/login')
}
const userAuth = () => (to, from, next) => {
  if (store.getters.menu) {
    store.dispatch('sideMenu', false)
  }
  Vue.swal({
    title: 'GitHub 아이디 찾기',
    text: '검색하고자 하는 GitHub 아이디를 입력하세요',
    icon: 'info',
    input: "text",
    showCloseButton: true,
    showCancelButton: true,
    focusConfirm: false,
    reverseButtons: true,
    focusCancel: true,
    cancelButtonText: `취소`,
    confirmButtonText: `입력`
  }).then((result) => {
    if (result.value) {
      store.state.wantuser = result.value
      Vue.swal({
        icon: "success",
        title: "열람 모드로 전환됩니다. 홈 버튼을 누를 경우 초기화 됩니다.",
      })
      next('/project')
    } else {
      next('/')
    }
  })
}
const sidebarClose = () => (to, from, next) => {
  if (store.getters.menu) {
    store.dispatch('sideMenu', false)
  }
  if (store.state.isLogin && store.state.wantuser) {
    Vue.swal({
      icon: "info",
      title: "관리자 모드로 전환됩니다.",
    })
    store.state.tempname = store.state.username
    store.state.wantuser = null
  } else {
    store.state.tempname = store.state.username
    store.state.wantuser = null
  }
  return next()
}
const algo = () => (to, from, next) => {
  if (store.getters.menu) {
    store.dispatch('sideMenu', false)
    Vue.swal({
      title: '<strong>모바일 화면 최적화 경고</strong>',
      icon: 'warning',
      html: `모바일 화면에서 적합하지 않은 요소들이 있습니다. 접속하시겠습니까?`,
      showCloseButton: true,
      showCancelButton: true,
      focusConfirm: false,
      reverseButtons: true,
      focusCancel: true,
      cancelButtonText: `취소`,
      confirmButtonText: `접속`
    }).then((result) => {
      if (result.value) {
        window.location.href = `http://i3a210.p.ssafy.io:8081/`
      }
    })
  } else {
    window.location.href = `http://i3a210.p.ssafy.io:8081/`
  }
}

var routes = [
  { path: '/', name: 'home', component: HomePage, beforeEnter: sidebarClose() },
  { path: '/introduce', name: 'introduce', component: IntroducePage, beforeEnter: requireAuthGuest() },
  { path: '/project', name: 'project', component: ProjectPage, beforeEnter: requireAuthGuest() },
  { path: '/projectcreate', name: 'createProject', component: CreateProject, beforeEnter: requireAuth() },
  { path: '/projectupdate', name: 'updateProject', component: UpdateProject, props: true, beforeEnter: requireAuth() },
  { path: '/algorithm', name: 'algorithm', component: AlgorithmPage, beforeEnter: algo() },
  { path: '/study', name: 'study', component: StudyPage, beforeEnter: requireAuthGuest() },
  { path: '/study/:page', name: 'study', component: StudyPage, beforeEnter: requireAuthGuest() },
  { path: '/studycreate', name: 'createArticle', component: CreateArticle, beforeEnter: requireAuth() },
  { path: '/study/:id', name: 'postPage', component: PostPage, beforeEnter: requireAuthGuest() },
  { path: '/study/:id/update', name: 'updateArticle', component: UpdateArticle, beforeEnter: requireAuth() },
  { path: '/study/:search', name: 'searchArticle', component: SearchArticle, beforeEnter: requireAuthGuest() },
  { path: '/study/:search/:page', name: 'searchArticle', component: SearchArticle, beforeEnter: requireAuthGuest() },
  { path: '/study/:category', name: 'categoryArticle', component: CategoryArticle, beforeEnter: requireAuthGuest() },
  { path: '/study/:category/:page', name: 'categoryArticle', component: CategoryArticle, beforeEnter: requireAuthGuest() },
  { path: '/study/:tag', name: 'tagArticle', component: TagArticle, beforeEnter: requireAuthGuest() },
  { path: '/study/:tag/:page', name: 'tagArticle', component: TagArticle, beforeEnter: requireAuthGuest() },
  { path: '/logsearch', name: 'logSearch', component: UserSearchPage, beforeEnter: userAuth() },
  { path: '/login', name: 'login', component: LoginPage, beforeEnter: sidebarClose() },
  { path: '/register', name: 'register', component: RegisterPage, beforeEnter: sidebarClose() },
  { path: '/user', name: 'user', component: UserPage, beforeEnter: requireAuth() },
  {
    path: '/logout',
    name: 'logout',
    beforeEnter (to, from, next) {
      Vue.swal({
        icon: "success",
        title: "로그아웃 되었습니다.",
      })
      store.dispatch('sideMenu', false)
      axios.get('/logout')
        .then(res => {
          store.state.isLogin = false
          store.state.username = null
          store.state.tempname = null
          store.state.wantuser = null
          next('/')
        })
    }
  },
  { path: '*', name: 'error', component: ErrorPage }
]

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
