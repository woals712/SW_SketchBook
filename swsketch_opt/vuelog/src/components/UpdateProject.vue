<template>
  <div class="container">
    <div class="form-v4-content">
      <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>

      <div class="form-left">
        <h2>프로젝트 수정하기</h2>
        <p class="text-1"><span>이미지 입력시 주의사항:</span><br>
        이미지는 URL 형식으로 변환해주시면 됩니다.<br>
        이미지가 없을 경우 카드 이미지 입력 창에 숫자 1 ~ 숫자 5 중 하나를 입력하세요.<br>
        디폴트 이미지 설정 가능하며, 이는 미리보기 화면에서 확인하실 수 있습니다.
        </p>
        <br>
        <h2>카드 미리보기</h2>
        <img class="card" id="outImage" :src="imageFunction"/>
        <img class="card2" id="inImage" :src="imageFunction" style="display:none;"/>
      </div>

      <form class="form-detail" @submit.prevent="submitForm">
        <h2>작성 폼</h2>
        <div class="form-group">
          <div class="form-row form-row-1">
            <label for="first_name">제목</label>
            <input v-model="projectInfo.title" type="text" class="input-text" id="titleInput" placeholder="프로젝트 제목" disabled>
          </div>
          <div class="form-row form-row-1">
            <label for="last_name">날짜</label>
            <input v-model="projectInfo.date" type="text" class="input-text" id="dateInput" placeholder="2020.01 - 2020.02">
          </div>
        </div>

        <div class="form-row">
          <label for="first_name">프로젝트 개요</label>
          <textarea v-model="projectInfo.details" id="detailArea" rows="3" style="resize: none;margin-bottom: 20px;"
          placeholder=
          "팀 구성 : 4명(팀장)
사용 기술 : Spring, Vue.js
사이트 : GitHub, YouTube"></textarea>
        </div>
        <div class="form-row">
          <label for="first_name">프로젝트 내용</label>
          <textarea v-model="projectInfo.content" id="contnetArea" rows="3" style="resize: none;margin-bottom: 20px;" placeholder="프로젝트 내용"></textarea>
        </div>
        <div class="form-group">
          <div class="form-row form-row-1">
            <label for="last_name">카드 이미지</label>
            <input v-model="projectInfo.outerImage" type="text" class="input-text" id="outerImageInput" placeholder="WEB image, 미선택시 기본화면">
          </div>
          <div class="form-row form-row-1">
            <label for="first_name">프로젝트 이미지</label>
            <input v-model="projectInfo.innerImage" type="text" class="input-text" id="innerImageInput" placeholder="WEB image OR 1~5까지 숫자입력">
          </div>
        </div>

        <div class="form-group">
          <div class="form-row form-row-1">
            <label for="first_name">깃허브 링크</label>
            <input v-model="projectInfo.githubLink" type="text" class="input-text" id="githubLinkInput" placeholder="github.com/깃허브 링크">
          </div>
          <div class="form-row form-row-1">
            <label for="last_name">유튜브 링크</label>
            <input v-model="projectInfo.youtubeLink" type="text" class="input-text" id="youtubeLinkInput" placeholder="youtube.com/링크">
          </div>
        </div>

        <div class="form-row-last">
          <button @click="UpdateProject" type="button" class="btn btn-primary">수정하기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'CreateProject',
  props: {
    id: {
      type: Number
    }
  },
  data () {
    return {
      projectInfo: {
        count: 0,
        title: '',
        date: '',
        details: '팀 구성 : \n사용 기술 : \n사이트 : ',
        content: '',
        innerImage: '',
        outerImage: '',
        githubLink: '',
        youtubeLink: '',
        imageMerge: [
          require("@/assets/img/coding.jpg"),
          require("@/assets/img/hackathon.jpg"),
          require("@/assets/img/marvel.jpg"),
          require("@/assets/img/treasure.jpg"),
          require("@/assets/img/codeback.png"),
          "assets/img/coding.jpg",
          "assets/img/hackathon.jpg",
          "assets/img/marvel.jpg",
          "assets/img/treasure.jpg",
          "assets/img/codeback.png",
        ],
      },
      errorMessage: '',
      innerNum: false,
      outerNum: false,
    }
  },
  computed: {
    imageFunction: function () {
      if ((this.projectInfo.outerImage.includes('asset') && this.projectInfo.outerImage.includes('coding')) || Number(this.projectInfo.outerImage) === 1) {
        return 'https://i.imgur.com/qPLKrcr.jpg'
      } else if ((this.projectInfo.outerImage.includes('asset') && this.projectInfo.outerImage.includes('hackathon')) || Number(this.projectInfo.outerImage) === 2) {
        return 'https://i.imgur.com/PEdOwiE.jpg'
      } else if ((this.projectInfo.outerImage.includes('asset') && this.projectInfo.outerImage.includes('marvel')) || Number(this.projectInfo.outerImage) === 3) {
        return 'https://i.imgur.com/N1r5s9P.jpg'
      } else if ((this.projectInfo.outerImage.includes('asset') && this.projectInfo.outerImage.includes('treasure')) || Number(this.projectInfo.outerImage) === 4) {
        return 'https://i.imgur.com/3dmC2IL.jpg'
      } else if ((this.projectInfo.outerImage.includes('asset') && this.projectInfo.outerImage.includes('codeback')) || Number(this.projectInfo.outerImage) === 5) {
        return 'https://i.imgur.com/IwOiwd0.png'
      }
      return this.projectInfo.outerImage
    }
  },
  methods: {
    UpdateProject () {
      if (!this.projectInfo.title) {
        this.$swal({
          icon: "warning",
          title: "제목을 입력해주세요",
        })
      } else if (!this.projectInfo.date) {
        this.$swal({
          icon: "warning",
          title: "날짜를 입력해주세요",
        })
      } else {
        if (!this.projectInfo.outerImage) {
          this.projectInfo.outerImage = this.projectInfo.imageMerge[5]
          this.outerNum = true
        } else if (Number(this.projectInfo.outerImage) === 1) {
          this.projectInfo.outerImage = 'https://i.imgur.com/qPLKrcr.jpg'
          this.outerNum = true
        } else if (Number(this.projectInfo.outerImage) === 2) {
          this.projectInfo.outerImage = 'https://i.imgur.com/PEdOwiE.jpg'
          this.outerNum = true
        } else if (Number(this.projectInfo.outerImage) === 3) {
          this.projectInfo.outerImage = 'https://i.imgur.com/N1r5s9P.jpg'
          this.outerNum = true
        } else if (Number(this.projectInfo.outerImage) === 4) {
          this.projectInfo.outerImage = 'https://i.imgur.com/3dmC2IL.jpg'
          this.outerNum = true
        } else if (Number(this.projectInfo.outerImage) === 5) {
          this.projectInfo.outerImage = 'https://i.imgur.com/IwOiwd0.png'
          this.outerNum = true
        } else {
          var outX = document.getElementById("outImage").naturalWidth
          if (!outX) {
            this.outerNum = false
            this.$swal({
              icon: "error",
              title: "카드 이미지 형식이 잘못 되었습니다",
            })
            return
          } else {
            this.outerNum = true
          }
        }

        if (!this.projectInfo.innerImage) {
          this.projectInfo.innerImage = this.projectInfo.outerImage
          this.innerNum = true
        } else if (Number(this.projectInfo.innerImage) === 1) {
          this.projectInfo.innerImage = 'https://i.imgur.com/qPLKrcr.jpg'
          this.innerImage = true
        } else if (Number(this.projectInfo.innerImage) === 2) {
          this.projectInfo.innerImage = 'https://i.imgur.com/PEdOwiE.jpg'
          this.innerImage = true
        } else if (Number(this.projectInfo.innerImage) === 3) {
          this.projectInfo.innerImage = 'https://i.imgur.com/N1r5s9P.jpg'
          this.innerImage = true
        } else if (Number(this.projectInfo.innerImage) === 4) {
          this.projectInfo.innerImage = 'https://i.imgur.com/3dmC2IL.jpg'
          this.innerImage = true
        } else if (Number(this.projectInfo.innerImage) === 5) {
          this.projectInfo.innerImage = 'https://i.imgur.com/IwOiwd0.png'
          this.innerImage = true
        } else {
          var inX = document.getElementById("inImage").naturalWidth
          if (!inX) {
            this.innerNum = false
            this.$swal({
              icon: "error",
              title: "프로젝트 이미지 형식이 잘못 되었습니다",
            })
            return
          } else {
            this.innerNum = true
          }
        }

        if (this.projectInfo.youtubeLink) {
          var strArray = this.projectInfo.youtubeLink.split('/')
          var temp = strArray[strArray.length - 1]

          this.projectInfo.youtubeLink = 'www.youtube.com/embed/' + temp
        }

        if (this.innerNum && this.outerNum) {
          axios.put(`/portfolio/${this.id}`, this.projectInfo)
            .then(res => {
              this.$router.push('/project')
            })
        }
      }
    },
    GetProject () {
      axios.get(`/portfolio/${this.id}`)
        .then(res => {
          this.projectInfo = res.data.Portfolio
          // console.log(res)
        })
    }
  },
  created () {
    this.GetProject()
  }
}
</script>

<style scoped>
body {
  margin: 0;
}

.container {
  width: 100%;
  margin: 0 auto;
  display: flex;
  display: -webkit-flex;
  justify-content: center;
  -o-justify-content: center;
  -ms-justify-content: center;
  -moz-justify-content: center;
  -webkit-justify-content: center;
  align-items: center;
  -o-align-items: center;
  -ms-align-items: center;
  -moz-align-items: center;
  -webkit-align-items: center;
}
.form-v4-content  {
  background: #fff;
  width: 1050px;
  border-radius: 10px;
  -o-border-radius: 10px;
  -ms-border-radius: 10px;
  -moz-border-radius: 10px;
  -webkit-border-radius: 10px;
  box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -o-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -ms-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -moz-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -webkit-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  margin: 30px 0;
  position: relative;
  display: flex;
  display: -webkit-flex;
}
.form-v4-content h2 {
  font-weight: 600;
  font-size: 31px;
  padding: 6px 0 0;
  margin-bottom: 34px;
  color: white;
}
.form-v4-content .form-left {
  background: #3786bd;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  padding: 20px 40px;
  position: relative;
  width: 60%;
  color: #fff;
}
.form-v4-content .form-left p {
  font-size: 15px;
  font-weight: 400;
  line-height: 1.5;
}
.form-v4-content .form-left span {
  font-weight: 700;
}
.form-v4-content .form-left .text-2 {
  margin: 20px 0 25px;
}
.form-v4-content .form-left .account {
  background: #fff;
  border-top-left-radius: 5px;
  border-bottom-right-radius: 5px;
  width: 180px;
  border: none;
  margin: 15px 0 50px 0px;
  cursor: pointer;
  color: #333;
  font-weight: 700;
  font-size: 15px;
  font-family: 'Open Sans', sans-serif;
  appearance: unset;
    -moz-appearance: unset;
    -webkit-appearance: unset;
    -o-appearance: unset;
    -ms-appearance: unset;
    outline: none;
    -moz-outline: none;
    -webkit-outline: none;
    -o-outline: none;
    -ms-outline: none;
}
.form-v4-content .form-left .account:hover {
  background: #e5e5e5;
}
.form-v4-content .form-left .form-left-last input {
  padding: 15px;
}
.form-v4-content .form-left .form-left-last textarea {
  padding: 15px;
}
.form-v4-content .form-detail {
  padding: 20px 40px;
  position: relative;
  width: 100%;
}
.form-v4-content .form-detail h2 {
  color: #666;
}
.form-v4-content .form-detail .form-group {
  display: flex;
  display: -webkit-flex;
  margin:  0 -8px;
}
.form-v4-content .form-detail .form-row {
  width: 100%;
  position: relative;
}
.form-v4-content .form-detail .form-group .form-row.form-row-1 {
  width: 50%;
  padding: 0 8px;
}
.form-v4-content .form-detail label {
  font-weight: 600;
  font-size: 15px;
  color: #666;
  display: block;
  margin-bottom: 8px;
}
.form-v4-content .form-detail .form-row label#valid {
    position: absolute;
    right: 20px;
    top: 50%;
    transform: translateY(-50%);
    -o-transform: translateY(-50%);
    -moz-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    -webkit-transform: translateY(-50%);
    width: 14px;
    height: 14px;
    border-radius: 50%;
    -o-border-radius: 50%;
    -ms-border-radius: 50%;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    background: #53c83c;
}
.form-v4-content .form-detail .form-row label#valid::after {
  content: "";
    position: absolute;
    left: 5px;
    top: 1px;
    width: 3px;
    height: 8px;
    border: 1px solid #fff;
    border-width: 0 2px 2px 0;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    -o-transform: rotate(45deg);
    -moz-transform: rotate(45deg);
    transform: rotate(45deg);
}
.form-v4-content .form-detail .input-text {
  margin-bottom: 27px;
}
.form-v4-content .form-detail .textarea {
  margin-bottom: 27px;
}
.form-v4-content .form-detail input {
  width: 100%;
    padding: 11.5px 15px;
    border: 1px solid #e5e5e5;
    border-top-left-radius: 5px;
    border-bottom-right-radius: 5px;
    appearance: unset;
    -moz-appearance: unset;
    -webkit-appearance: unset;
    -o-appearance: unset;
    -ms-appearance: unset;
    outline: none;
    -moz-outline: none;
    -webkit-outline: none;
    -o-outline: none;
    -ms-outline: none;
    font-family: 'Open Sans', sans-serif;
    font-size: 15px;
    color: #333;
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -o-box-sizing: border-box;
    -ms-box-sizing: border-box;
}
.form-v4-content .form-detail textarea {
  width: 100%;
    padding: 11.5px 15px;
    border: 1px solid #e5e5e5;
    border-top-left-radius: 5px;
    border-bottom-right-radius: 5px;
    appearance: unset;
    -moz-appearance: unset;
    -webkit-appearance: unset;
    -o-appearance: unset;
    -ms-appearance: unset;
    outline: none;
    -moz-outline: none;
    -webkit-outline: none;
    -o-outline: none;
    -ms-outline: none;
    font-family: 'Open Sans', sans-serif;
    font-size: 15px;
    color: #333;
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -o-box-sizing: border-box;
    -ms-box-sizing: border-box;
}
.form-v4-content .form-detail .form-row input:focus {
  border: 1px solid #53c83c;
}
.form-v4-content .form-detail .form-row textarea:focus {
  border: 1px solid #53c83c;
}
.form-v4-content .form-detail .register {
  background: #3786bd;
  border-top-left-radius: 5px;
  border-bottom-right-radius: 5px;
  width: 130px;
  border: none;
  margin: 6px 0 50px 0px;
  cursor: pointer;
  color: #fff;
  font-weight: 700;
  font-size: 15px;
}
.form-v4-content .form-detail .register:hover {
  background: #2f73a3;
}
.form-v4-content .form-detail .form-row-last input {
  padding: 12.5px;
}
.form-v4-content .form-detail .form-row-last textarea {
  padding: 12.5px;
}
.card {
  position: relative;
  height: 400px;
  width: 300px;
  box-shadow: 0 5px 20px rgba(120, 120, 120, 0.4);
  border: none;
  border-radius: 20px;
  margin: 20px;
  transition: all 500ms ease-out;
}

/* Responsive */
@media screen and (max-width: 991px) {
  .form-v4-content {
    margin: 180px 20px;
    flex-direction:  column;
    -o-flex-direction:  column;
    -ms-flex-direction:  column;
    -moz-flex-direction:  column;
    -webkit-flex-direction:  column;
  }
  .form-v4-content .form-left {
    width: auto;
    border-top-right-radius: 10px;
    border-bottom-left-radius: 0;
  }
  .form-v4-content .form-detail {
    padding: 30px 20px 30px 20px;
      width: auto;
  }
}
@media screen and (max-width: 575px) {
  .form-v4-content .form-detail .form-group {
    flex-direction: column;
    -o-flex-direction:  column;
    -ms-flex-direction:  column;
    -moz-flex-direction:  column;
    -webkit-flex-direction:  column;
    margin: 0;
  }
  .form-v4-content .form-detail .form-group .form-row.form-row-1 {
    width: 100%;
    padding:  0;
  }
}
</style>
