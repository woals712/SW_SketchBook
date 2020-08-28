import axios from 'axios'
import errorParser from '@/utils/error-parser'
import vuex from '@/store'

export default {
  /**
   * Authenticate a login request
   * @param {Object} detail login detail
   */
  authenticate (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/authentications', detail).then(({ data }) => {
        axios.get('/me')
          .then(res => {
            vuex.state.username = res.data.user.name
          })
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
