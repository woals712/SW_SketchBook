// 01. 사이드바 STORE
export function menu (state) {
  return state.menu
}

// 02. 메뉴 STORE (소개, 프로젝트, 알고리즘, STUDY, 로그인)
export function navigationUser (state) {
  return state.database.nav_user_list_login
}

export function navigationNonuser (state) {
  return state.database.nav_user_list_logout
}
