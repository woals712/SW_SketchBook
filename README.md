### SW_Sketchbook(개발자 맞춤 포트폴리오 관리, 알고리즘 채점, 개인 블로그 제공 웹서비스)
  -> 소개 동영상 링크 :https://youtu.be/fhAD9aMtuWU
*Front end(Vue.js)
  * Github ID와의 연동을 통해 사용자 검색 시 Github 프로필 UI 제공
  * 모바일 환경을 고려한 반응형 앱 적용
  * 카드 형식의 메인 페이지를 통한 직관적인 UI 제공
  * 포트폴리오 추가 시 Github,Youtube 링크 추가 가능


* Back end(Spring)
  * AWS, Docker를 활용한 WAS, DB 관리
  * 각각의 기능에 대한 Restful API 구현, 해당 Http 반환값에 요청 결과에 대한 코드+메세지를 통한 예외처리
  * 알고리즘 채점 서비스는 알고리즘 채점 오픈 소스를 Docker에 올려서 활용
  * 웹 소켓을 통한 실시간 서비스 구현