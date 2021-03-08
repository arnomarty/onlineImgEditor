<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <button class="Search__button" @click="getSpecificPicture()">Call Spring Boot REST backend</button>
    <h3>{{ response }}</h3>
  </div>
  <div id="v-model-select-dynamic" class="demo">
    <select v-model="selected">
      <option v-for="img in response" :key="img.id" :value="img.id">
        {{ img.name }}
      </option>
    </select>
    <span>Selected: {{ selected }}</span>
  </div>
  // The issue probably lies down here:
  <p>
    <img v-bind:src="imageUrl">
  </p>
</template>

<script>
import axios from 'axios'
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data () {
    return {
      response: [],
      errors: [],
      selected: '0',
      imageUrl: 'images/0'
    }
  },
  methods: {
    // Useless until the POST method is implemented
    callRestService () {
      axios
        .get('images')
        .then((response) => {
          // JSON responses are automatically parsed.
          this.response = response.data
        })
        .catch((e) => {
          this.errors.push(e)
        })
    },
    // Downloads the image client-side, but it doesn't show on the HTML
    getSpecificPicture () {
      var link = 'images/' + this.selected
      axios.get(link, { responseType: 'blob' })
        .then(function (response) {
          var reader = new window.FileReader()
          reader.readAsDataURL(response.data)
          reader.onload = function () {
            this.imageUrl = reader.result
            // this.imageUrl = link
          }
        })
    }
  },
  mounted: function () {
    axios
      .get('images')
      .then((response) => {
        // JSON responses are automatically parsed.
        this.response = response.data
      })
      .catch((e) => {
        this.errors.push(e)
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
