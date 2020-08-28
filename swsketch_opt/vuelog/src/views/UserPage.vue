<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="form">
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
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
            <label for="password2">새 비밀번호</label>
            <input type="password" class="form-control" id="password2" v-model="form.newpassword">
            <div class="field-error" v-if="$v.form.newpassword.$dirty">
              <div style="color:red;" v-if="!$v.form.newpassword.required">비밀번호가 잘못되었습니다</div>
              <div style="color:red;" v-if="!$v.form.newpassword.minLength">비밀번호가 너무 짧습니다 ( {{$v.form.password.$params.minLength.min}} 자 이상 )</div>
              <div style="color:red;" v-if="!$v.form.newpassword.maxLength">비밀번호가 너무 깁니다 ( {{$v.form.password.$params.maxLength.max}} 자 이상 )</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password3">새 비밀번호 확인</label>
            <input type="password" class="form-control" id="password3" v-model="form.checknewpswd">
            <div class="field-error" v-if="$v.form.checknewpswd.$dirty">
              <div style="color:red;" v-if="!$v.form.checknewpswd.required">비밀번호가 잘못되었습니다</div>
              <div style="color:red;" v-if="!$v.form.checknewpswd.minLength">비밀번호가 너무 짧습니다 ( {{$v.form.password.$params.minLength.min}} 자 이상 )</div>
              <div style="color:red;" v-if="!$v.form.checknewpswd.maxLength">비밀번호가 너무 깁니다 ( {{$v.form.password.$params.maxLength.max}} 자 이상 )</div>
              <div style="color:red;" v-if="form.newpassword !== form.checknewpswd">비밀번호가 일치하지 않습니다</div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block">비밀번호 변경</button>
          <!-- <p class="accept-terms text-muted">회원가입 할 시, 포트폴리오 사이트에 의견을 개시할 수 있습니다</p> -->
          <p class="accept-terms text-muted">비밀번호 변경 후, 다시 로그인해 주시기 바랍니다</p>
          <p class="text-center text-muted"><a class="plusB btn btn-danger text-white" @click="deleteuser">계정 삭제</a></p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { required, minLength, maxLength } from 'vuelidate/lib/validators'
import changeService from '@/services/change'
import axios from 'axios'

export default {
  name: 'UserPage',
  data: function () {
    return {
      form: {
        password: '',
        newpassword: '',
        checknewpswd: '',
      },
      errorMessage: '',
      delete: {
        password: ''
      }
    }
  },
  validations: {
    form: {
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      },
      newpassword: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      },
      checknewpswd: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      }
    }
  },
  created () {
    axios.get('/me')
      .then(res => {
        this.$store.state.username = res.data.user.name
        // console.log(this.$store.state.username)
      })
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      changeService.register(this.form).then(() => {
        this.$swal({
          icon: "success",
          title: "비밀번호 변경 완료!",
        })
        this.$store.state.username = null
        this.$store.state.tempname = null
        this.$store.state.isLogin = false
        this.$router.push('/login')
      }).catch((error) => {
        this.errorMessage = 'Failed to register user. ' + error.message
      })
    },
    deleteuser () {
      this.$swal({
        title: '계정 삭제',
        text: '비밀번호를 입력하세요',
        icon: 'error',
        input: "password",
        showCloseButton: true,
        showCancelButton: true,
        focusConfirm: false,
        reverseButtons: true,
        focusCancel: true,
        cancelButtonText: `취소`,
        confirmButtonText: `삭제`
      }).then((result) => {
        if (result.value) {
          this.delete.password = result.value
          axios.delete('/deleteuser', { data: { password: result.value } })
            .then(res => {
              this.$swal({
                icon: "success",
                title: "계정이 삭제되었습니다."
              })
              this.$store.state.username = null
              this.$store.state.tempname = null
              this.$store.state.isLogin = false
              this.$router.push('/')
            })
        }
      })
    },
    // createCategory (nametemp) {
    //   this.newCategory.name = nametemp
    //   axios.post('/category', this.newCategory)
    //     .then(res => {
    //       this.newCategory.name = ''
    //       this.$swal({
    //         icon: "success",
    //         title: "카테고리가 생성되었습니다",
    //       })
    //       axios.get(`/${this.$store.state.username}/category`)
    //         .then(res => {
    //           this.myCategory = res.data.categoryList
    //         })
    //     })
    //     .catch(err => alert(err))
    // },
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
