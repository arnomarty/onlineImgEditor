<template>
  <div class="hello">
      <button class="Search__button" @click="callRestService()">Call Spring Boot REST backend</button>
      <h3>{{ response }}</h3>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ImageIO',
  props: {
    url: String
  },
  data () {
    return {
      response: [],
      errors: []
    }
  },
  watch: {

  },
  methods: {
    callRestService () {
      axios
        .get('algorithms')
        .then((response) => {
          // JSON responses are automatically parsed.
          this.response = response.data
        })
        .catch((e) => {
          this.errors.push(e)
        })
    }

  }
}
</script>
