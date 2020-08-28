import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * Register a new user
   * @param {Object} detail registration detail
   */
  create (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/study', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
