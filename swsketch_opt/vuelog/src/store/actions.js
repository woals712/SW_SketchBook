import axios from 'axios'
// import router from '../router'

// 01. 사이드바 키고 닫는 function
export function sideMenu ({ commit, state }, visibility) {
  state.menu = visibility
}

export function login ({ state, dispatch }, loginObj) {
  axios.post('/authentications', loginObj)
    .then(res => {
      state.isLogin = true
      dispatch('getusername')
    })
}

export function logout ({ state }) {
  axios.get('/logout')
    .then(res => {
      state.isLogin = false
    })
}

export function getusername ({ state }) {
  axios.get('/me')
    .then(res => {
      state.username = res.data.user.name
    })
}

// export function introduce () {
//   axios.post('/introduce', { detail: 'hello' })
//     .then(res => {
//       console.log(res)
//     })
// }
