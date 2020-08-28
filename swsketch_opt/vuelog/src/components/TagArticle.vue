<template>
  <div class="study">
    <p class="plusB btn btn-danger" v-if="!tempname && wantuser">{{ wantuser }} 아이디 열람 모드</p>
    <div class="container">
      <div class="row">
        <div class="col-md-8">
          <h2 style="color:#34495e;padding-bottom:30px;">태그 검색 결과 : '{{ $route.params.tag }}'</h2>
          <div v-for="study in myStudy" :key="study.id">
            <div class="card mb-4">
              <div class="card-body">
                <h3 class="card-title" style="color:#34495e">{{ study.title }}</h3>
                <p v-if="study.content.length < 100" class="card-text">{{ study.content }}</p>
                <p v-else class="card-text">{{ study.content.substr(0, 100) }}...</p>
                <a class="btn btn-primary"><router-link :to="{ name: 'postPage', params: { id: study.id } }" class="text-white">더 보기 &rarr;</router-link></a>
                <a class="plusB btn btn-warning" v-if="tempname"><router-link :to="{ name: 'updateArticle', params: { id: study.id } }" class="text-white">수정</router-link></a>
                <a class="plusB btn btn-danger text-white" @click="deleteStudy(study.id)" v-if="tempname">삭제</a>
              </div>
              <div class="card-footer text-muted d-flex justify-content-between">
                <div>
                  <div v-for="tag in study.tagNameList" :key="tag" class="btn-group">
                    <button type="button" class="btn btn-secondary mr-2"><router-link :to="{ name: 'tagArticle', params: { tag: tag}}" class="text-white">#{{ tag }}</router-link></button>
                  </div>
                </div>
                <p class="my-1">작성자 : {{ study.username }}</p>
              </div>
            </div>
          </div>

          <ul class="pagination justify-content-center mb-4">
            <li class="page-item">
              <!-- <a class="page-link" v-if="pageData.page > 1" :href="`/study/${$route.params.category}?page=${beforepage}`">&larr; 이전</a> -->
              <button class="page-link" v-if="pageData.page > 1"><router-link :to="{ name: 'tagArticle', params: { tag: $route.params.tag }, query: { page : beforepage }}">&larr; 이전</router-link></button>
            </li>
            <p class="my-2 mx-2" v-if="pageData.listCnt != 0">{{ pageData.page }}/{{ pageData.endPage }}</p>
            <li class="page-item">
              <!-- <a class="page-link" v-if="pageData.page < pageData.endPage" :href="`/study/${$route.params.category}?page=${nextpage}`">다음 &rarr;</a> -->
              <button class="page-link" v-if="pageData.page < pageData.endPage"><router-link :to="{ name: 'tagArticle', params: { tag: $route.params.tag }, query: { page : nextpage }}">다음 &rarr;</router-link></button>
            </li>
          </ul>
        </div>

        <div class="col-md-4">
          <div class="card mb-4">
            <h5 class="card-header">관리</h5>
            <div class="card-body">
              <div class="row">
                <a class="plusB btn btn-success text-white" @click="createAlert" v-if="tempname">카테고리 생성</a>
                <button class="plusB btn btn-info" v-if="tempname"><router-link :to="{ name: 'createArticle' }" class="text-white text-decoration-none">글 작성</router-link></button>
                <a class="plusB btn btn-primary text-white mt-2" @click="deletewantuser" v-if="!tempname && username">내 게시글</a>
              </div>
            </div>
          </div>

          <div class="card my-4">
            <h5 class="card-header">검색</h5>
            <div class="card-body">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="내용" v-model="search">
                <span class="input-group-append">
                  <button class="btn btn-secondary" type="button"><router-link :to="{ name : 'searchArticle', params: { search: search }}" class="text-white">검색</router-link></button>
                </span>
              </div>
            </div>
          </div>

          <div class="card my-4">
            <div class="card-header d-flex justify-content-between align-items-center">
              <h5 class="mb-0">카테고리</h5>
            </div>
            <div class="card-body">
              <div v-for="category in myCategory" :key="category.id">
                <div class="btn-group btn-block">
                  <button type="button" class="btn btn-outline-secondary d-flex justify-content-between" aria-haspopup="true" aria-expanded="false">
                    <router-link :to="{ name: 'categoryArticle', params: { category: category.name }}" class="text-dark">{{ category.name }}({{ category.count }})</router-link>
                    <button v-if="tempname" class="plusB btn btn-danger btn-sm" @click="deletearticle(category.name, category.id)">삭제</button>
                  </button>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  name: 'TagArticlePage',
  data: function () {
    return {
      newCategory: {
        name: ''
      },
      myCategory: [],
      myStudy: [],
      search: '',
      pageData: [],
    }
  },
  computed: {
    ...mapState(['username']),
    ...mapState(['tempname']),
    ...mapState(['wantuser']),
    nextpage () {
      if (this.$route.query.page) {
        return this.$route.query.page * 1 + 1
      } else {
        return 2
      }
    },
    beforepage () {
      return this.$route.query.page - 1
    }
  },
  methods: {
    deletearticle (cname, cid) {
      this.$swal({
        title: `${cname} 글 전체 \n 삭제하시겠습니까?`,
        text: "더 이상 복구할 수 없습니다",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
      }).then((result) => {
        if (result.value) {
          this.$swal(
            '삭제되었습니다!',
            '파일이 삭제되었습니다',
            'success'
          )
          axios.delete(`/category/${cid}`)
            .then(res => {
              axios.get(`/${this.$store.state.username}/category`)
                .then(res => {
                  this.myCategory = res.data.categoryList
                  this.$router.push('/study')
                })
            })
        }
      })
    },
    deletewantuser () {
      this.$store.state.wantuser = null
      this.$router.push('/study')
    },
    deleteStudy (id) {
      this.$swal({
        title: '정말 삭제하시겠습니까?',
        text: "더 이상 복구할 수 없습니다",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
      }).then((result) => {
        if (result.value) {
          this.$swal(
            '삭제되었습니다!',
            '파일이 삭제되었습니다',
            'success'
          )
          axios.delete(`/study/${id}`)
            .then(res => {
              // this.$router.push('/study')
              if (this.$route.query.page * 1 > 1) {
                axios.get(`/study/tag/${this.$route.params.tag}?page=${this.$route.query.page}`)
                  .then(res => {
                    this.myStudy = res.data.studyList
                    this.pageData = res.data.pageData
                  })
              } else {
                axios.get(`/study/tag/${this.$route.params.tag}`)
                  .then(res => {
                    this.myStudy = res.data.studyList
                    this.pageData = res.data.pageData
                  })
              }
            })
        }
      })
    },
    createCategory (nametemp) {
      this.newCategory.name = nametemp
      axios.post('/category', this.newCategory)
        .then(res => {
          this.newCategory.name = ''
          this.$swal({
            icon: "success",
            title: "카테고리가 생성되었습니다",
          })
          axios.get('/category')
            .then(res => {
              this.myCategory = res.data.categoryList
            })
        })
        .catch(err => alert(err))
    },
    createAlert () {
      this.$swal({
        title: '카테고리 입력',
        text: '원하는 카테고리를 입력하세요',
        icon: 'info',
        input: "text",
        showCloseButton: true,
        showCancelButton: true,
        focusConfirm: false,
        reverseButtons: true,
        focusCancel: true,
        cancelButtonText: `취소`,
        confirmButtonText: `생성`
      }).then((result) => {
        if (result.value) {
          this.createCategory(result.value)
        }
      })
    }
  },
  created () {
    if (this.$store.state.wantuser) {
      axios.get(`/${this.$store.state.wantuser}/category`)
        .then(res => {
          this.myCategory = res.data.categoryList
        })
    } else {
      axios.get(`/${this.$store.state.username}/category`)
        .then(res => {
          this.myCategory = res.data.categoryList
        })
    }
    if (this.$route.query.page * 1 > 1) {
      axios.get(`/study/tag/${this.$route.params.tag}?page=${this.$route.query.page}`)
        .then(res => {
          this.myStudy = res.data.studyList
          this.pageData = res.data.pageData
        })
    } else {
      axios.get(`/study/tag/${this.$route.params.tag}`)
        .then(res => {
          this.myStudy = res.data.studyList
          this.pageData = res.data.pageData
        })
    }
  }
}
</script>

<style lang="stylus" scoped>
  .study {
    margin-top 20px
  }
  .hashtag {
    padding-bottom 10px
  }
  .btn-block {
    padding-bottom 3px
  }
  .plusB {
    margin-left 15px
  }
  .dropdown-toggle::after {
    display:none;
  }
  @media screen and (max-width: 999px)
    h4
      margin-bottom .5em

    ul
      padding-left 1em

    ul
      padding-left .75em
  .modal {
    text-align: center;
  }

  @media screen and (min-width: 768px) {
    .modal:before {
        display: inline-block;
        vertical-align: middle;
        content: " ";
        height: 100%;
    }
  }

  .modal-dialog {
    display: inline-block;
    text-align: left;
    vertical-align: middle;
  }
</style>
