<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="form">
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">GitHub 아이디</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div style="color:red;" v-if="!$v.form.username.required">아이디가 잘못되었습니다</div>
              <div style="color:red;" v-if="!$v.form.username.alphaNum">아이디는 문자나 숫자 형태로 구성해주세요</div>
              <div style="color:red;" v-if="!$v.form.username.minLength">아이디의 길이가 짧습니다 ( {{$v.form.username.$params.minLength.min}} 자 이상 )</div>
              <div style="color:red;" v-if="!$v.form.username.maxLength">아이디가 너무 깁니다 ( {{$v.form.username.$params.maxLength.max}} 자 이상 )</div>
            </div>
          </div>
          <div class="form-group">
            <label for="emailAddress">이메일</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress">
            <div class="field-error" v-if="$v.form.emailAddress.$dirty">
              <div style="color:red;" v-if="!$v.form.emailAddress.required">이메일이 잘못되었습니다</div>
              <div style="color:red;" v-if="!$v.form.emailAddress.email">이메일의 형태가 아닙니다</div>
              <div style="color:red;" v-if="!$v.form.emailAddress.maxLength">이메일이 너무 깁니다 ( {{$v.form.emailAddress.$params.maxLength.max}} 자 이상 )</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div style="color:red;" v-if="!$v.form.password.required">비밀번호가 잘못되었습니다</div>
              <div style="color:red;" v-if="!$v.form.password.minLength">비밀번호가 너무 짧습니다 ( {{$v.form.password.$params.minLength.min}} 자 이상 )</div>
              <div style="color:red;" v-if="!$v.form.password.maxLength">비밀번호가 너무 깁니다 ( {{$v.form.password.$params.maxLength.max}} 자 이상 )</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password2">비밀번호 확인</label>
            <input type="password" class="form-control" id="password2" v-model="form.checkpswd">
            <div class="field-error" v-if="$v.form.checkpswd.$dirty">
              <div style="color:red;" v-if="!$v.form.checkpswd.required">비밀번호가 잘못되었습니다</div>
              <div style="color:red;" v-if="!$v.form.checkpswd.minLength">비밀번호가 너무 짧습니다 ( {{$v.form.password.$params.minLength.min}} 자 이상 )</div>
              <div style="color:red;" v-if="!$v.form.checkpswd.maxLength">비밀번호가 너무 깁니다 ( {{$v.form.password.$params.maxLength.max}} 자 이상 )</div>
              <div style="color:red;" v-if="form.password !== form.checkpswd">비밀번호가 일치하지 않습니다</div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">회원가입</button>
          <p class="accept-terms text-muted">회원가입 할 시, 포트폴리오 사이트에 의견을 개시할 수 있습니다</p>
          <p class="text-center text-muted">이미 회원가입 하셨나요? <a href="/login">로그인</a></p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { required, email, minLength, maxLength, alphaNum } from 'vuelidate/lib/validators'
import registrationService from '@/services/registration'
import { mapActions } from 'vuex'

export default {
  name: 'RegisterPage',
  data: function () {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: '',
        checkpswd: '',
      },
      errorMessage: '',
    }
  },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alphaNum
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      },
      checkpswd: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      }
    }
  },
  methods: {
    ...mapActions(['introduce']),
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      registrationService.register(this.form).then(() => {
        this.$swal({
          icon: "success",
          title: "회원가입 완료!",
        })
        this.$router.push({ name: 'login' })
      }).catch((error) => {
        this.errorMessage = 'Failed to register user. ' + error.message
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.public {
  margin-top: 75px
}

.accept-terms {
  margin: 20px 0 40px 0;
}
</style>
