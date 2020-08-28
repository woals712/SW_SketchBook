<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="form">
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">GitHub 아이디 (이메일)</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div style="color:red;" v-if="!$v.form.username.required">아이디를 다시 입력해주세요</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div style="color:red;" v-if="!$v.form.password.required">비밀번호를 다시 입력해주세요</div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block" @click="login({ username: form.username, password: form.password })">로그인</button>
          <div>
          </div>
          <div class="links">
           <p class="sign-up text-muted">회원이 아니신가요? 모든 기능을 이용해 보세요 <a href="/register" class="link-sign-up">회원가입</a></p>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import authenticationService from '@/services/authentication'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'LoginPage',
  data: function () {
    return {
      form: {
        username: '',
        password: ''
      },
      errorMessage: '',
      CLIENT_ID: 'LGsvqPrY7NmBjZkeP0Rg',
      redirectURI: 'http://localhost:8080/naver/callback1',
      state: 123,
      githubLoginURL: 'http://localhost:8080/oauth2/authorization/github'
    }
  },
  validations: {
    form: {
      username: {
        required
      },
      password: {
        required
      }
    }
  },
  computed: {
    ...mapState(['isLogin', 'isLoginError'])
  },
  methods: {
    ...mapActions(['login']),
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }
      authenticationService.authenticate(this.form).then((res) => {
        this.$swal({
          icon: "success",
          title: "로그인 완료!",
        })
        axios.get('/me')
          .then(res => {
            this.$store.state.username = res.data.user.name
            // console.log(this.$store.state.username)
            this.$router.push({ name: 'home' })
          })
      }).catch((error) => {
        this.errorMessage = error.message
        this.$router.push({ name: 'login' })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.public {
  margin-top: 100px
}

.links {
  margin: 30px 0 50px 0;
  text-align: center;
}

img {
  margin-top: 7px;
  height: 53px;
  width: 300px;
}
</style>
