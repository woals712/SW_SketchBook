<template>
  <panel>
    <div slot="title">SwSketchBook 온라인 저지 가이드</div>
    <div class="content markdown-body">
      <ul>
        <li>01. 로그인, 회원가입 가이드
          <p>관리자 계정 로그인 방법 [아이디 : root, 비밀번호 : rootroot]<br>
          (관리자 계정 - 관련 공부한 문제 제출, 삭제 가능)<br><br>

          게스트 계정 로그인 방법 [아이디 : guest, 비밀번호 : guestguest]<br>
          (게스트 계정 - 문제 제출 가능)<br>
          (회원가입을 통해 가입 시 자동으로 게스트 계정으로 가입됩니다)<br><br>
          </p>
        </li>

        <li>02. 문제
          <p>문제 페이지에서 문제 열람, 코드 작성 가능<br>
          상세 페이지 - 제출에서 제출한 내역을 확인할 수 있습니다<br><br>

          관리자 페이지에서 문제를 만들 수 있습니다<br>
          예) 1.in (input 예제), 1.out (output 예제) 파일을 만들어 압축시키고, 압축 시킨 파일을 올리게 되면 예제 케이스가 올라갑니다.
          </p>
        </li>
      </ul>
    </div>
  </panel>
</template>

<script>
  export default {
    name: 'home'
  }
</script>

<style lang="less" scoped>
  .content {
    font-size: 16px;
    margin: 0 50px 40px 50px;
    > ul {
      list-style: disc;
      li {
        font-size: 16px;
        margin-top: 20px;
        &:first-child {
          margin-top: 0;
        }
        p {
          font-size: 14px;
          margin-top: 5px;
        }
      }
    }
  }
</style>
