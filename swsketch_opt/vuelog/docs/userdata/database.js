window.SwSketchbook = {
  database: {
    config: {
      brand: 'SwSketchbook',

      logo: 'logo.png',

      base: '/',
      homePath: '/home',
    },
    nav_user_list_login: [
      {
        label: '소개',
        name: 'introduce',
        path: '/introduce'
      },
      {
        label: '프로젝트',
        name: 'project',
        path: '/project'
      },
      {
        label: '알고리즘',
        name: 'algorithm',
        path: '/algorithm'
      },
      {
        label: 'STUDY',
        name: 'study',
        path: '/study?page=1'
      },
      {
        label: '로그아웃',
        type: 'dropdown',
        children: [
          {
            label: '유저 찾기',
            type: 'outgoing',
            link: '/logsearch'
          },
          {
            label: '회원 정보 관리',
            type: 'outgoing',
            link: '/user'
          },
          {
            label: '로그아웃',
            type: 'outgoing',
            link: '/logout'
          }
        ]
      }
    ],
    nav_user_list_logout: [
      {
        label: '소개',
        name: 'introduce',
        path: '/introduce'
      },
      {
        label: '프로젝트',
        name: 'project',
        path: '/project'
      },
      {
        label: '알고리즘',
        name: 'algorithm',
        path: '/algorithm'
      },
      {
        label: 'STUDY',
        name: 'study',
        path: '/study?page=1'
      },
      {
        label: '로그인',
        type: 'dropdown',
        children: [
          {
            label: '유저 찾기',
            type: 'outgoing',
            link: '/logsearch'
          },
          {
            label: '로그인',
            type: 'outgoing',
            link: '/login'
          },
          {
            label: '회원가입',
            type: 'outgoing',
            link: '/register'
          }
        ]
      }
    ],
  }
}
