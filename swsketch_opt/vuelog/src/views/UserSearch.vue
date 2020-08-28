<template>
  <div class="container public">
    <div class="row justify-content-center">
      <div class="form">
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">Github 아이디 찾기</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div style="color:red;" v-if="!$v.form.username.required">아이디를 입력해주세요</div>
            </div>
          </div>
          <button type="submit" class="btn btn-primary btn-block" @click="search(form.username)">찾기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import { mapState } from 'vuex'
// import axios from 'axios'

export default {
  name: 'LoginPage',
  data: function () {
    return {
      form: {
        username: ''
      },
      errorMessage: ''
    }
  },
  validations: {
    form: {
      username: {
        required
      }
    }
  },
  computed: {
    ...mapState(['isLogin'])
  },
  methods: {
    submitForm (serachname) {
      this.$store.state.wantuser = serachname
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
