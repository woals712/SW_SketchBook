<template>
  <div class="posts">
    <p class="plusB btn btn-danger" v-if="!tempname && wantuser">{{ wantuser }} 아이디 열람 모드</p>
    <div class="container">
      <div class="row">
        <div class="col-md-12">
            <h2 style="color:#34495e;padding-bottom:10px;" class="mt-4">{{ mystudy.title }}</h2>
            <p class="lead">
            <p>작성자 : {{ mystudy.username}}</p>
            <hr>
            <p>2020.08.21</p>
            <!-- <hr>
            <img class="img-fluid rounded" src="http://placehold.it/900x300" alt=""> -->
            <hr>
            <p class="lead">{{ mystudy.content }}</p>
            <hr>

            <div v-for="tag in mystudy.tagNameList" :key="tag" class="btn-group" style="padding-bottom:50px;">
              <button type="button" class="btn btn-secondary mr-2"><router-link :to="{ name: 'tagArticle', params: { tag: tag}}" class="text-white">#{{ tag }}</router-link></button>
            </div>

            <div class="card my-4">
            <h5 class="card-header">댓글</h5>
            <div class="card-body">
                <form>
                <div class="form-group">
                    <textarea class="form-control" rows="3" v-model="detail.detail"></textarea>
                </div>
                </form>
                <button type="submit" class="btn btn-primary" @click="submitComment(detail)">제출</button>
            </div>
            </div>

            <div v-for="comment in comments" :key="comment.id">
              <div v-if="comment.username === $store.state.username" class="media mb-4">
                <img class="d-flex mr-3 rounded-circle" src="../assets/img/commituser.png" alt="">
                <div class="media-body">
                    <h5 class="mt-0">{{ comment.username }}</h5>
                    <!-- <p v-if="!commentupdate">{{ comment.detail }}</p>
                    <input v-else-if="comment.id === commentid" v-model="editcomment.detail" type="text">
                     -->
                    <input v-if="comment.id === commentid && commentupdate" v-model="editcomment.detail" type="text" style="width:80%;">
                    <p v-else>{{ comment.detail }}</p>
                </div>
                <button v-if="!commentupdate" class="btn btn-warning" @click="updatecomment(comment.id, comment.detail)">수정</button>
                <button v-else-if="commentupdate && commentid === comment.id" class="btn btn-primary" @click="update(comment.id, editcomment)">제출</button>
                <button v-if="!commentupdate" class="btn btn-danger" @click="deletecomment(comment.id)">삭제</button>
              </div>
              <div v-else class="media mb-4">
                <img class="d-flex mr-3 rounded-circle" src="../assets/img/commituser.png" alt="">
                <div class="media-body">
                    <h5 class="mt-0">{{ comment.username }}</h5>
                    <p>{{ comment.detail }}</p>
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
  name: 'PostPage',
  data: function () {
    return {
      newCategory: {
        name: ''
      },
      detail: {
        detail: ''
      },
      comments: [],
      mystudy: [],
      myCategory: [],
      search: '',
      commentupdate: false,
      editcomment: {
        detail: ''
      },
      commentid: null
    }
  },
  computed: {
    ...mapState(['username']),
    ...mapState(['tempname']),
    ...mapState(['wantuser']),
  },
  methods: {
    updatecomment (cid, cdetail) {
      this.commentupdate = true
      this.editcomment.detail = cdetail
      this.commentid = cid
      axios.get(`/study/${this.$route.params.id}/`)
    },
    update (cid, detail) {
      axios.put(`/study/${this.$route.params.id}/comment/${cid}`, detail)
        .then(res => {
          axios.get(`/study/${this.$route.params.id}/allcomments`)
            .then(res2 => {
              this.comments = res2.data.comments
              this.commentupdate = false
            })
        })
    },
    deletecomment (cid) {
      axios.delete(`/study/${this.$route.params.id}/comment/${cid}`)
        .then(res => {
          axios.get(`/study/${this.$route.params.id}/allcomments`)
            .then(res2 => {
              this.comments = res2.data.comments
            })
        })
    },
    submitComment (detail) {
      axios.post(`/study/${this.$route.params.id}/comment`, detail)
        .then(res => {
          axios.get(`/study/${this.$route.params.id}/allcomments`)
            .then(res2 => {
              this.comments = res2.data.comments
              this.detail.detail = ''
            })
        })
    },
    createCategory () {
      axios.post('/category', this.newCategory)
        .then(res => {
          this.newCategory.name = ''
          alert('카테고리가 생성되었습니다.')
        })
        .catch(err => alert(err))
    }
  },
  created () {
    axios.get(`/study/${this.$route.params.id}/allcomments`)
      .then(res => {
        this.comments = res.data.comments
      })
    axios.get(`/study/${this.$route.params.id}`)
      .then(res => {
        this.mystudy = res.data.StudyData
      })
  }
}
</script>

<style lang="stylus" scoped>
  .posts {
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
</style>
