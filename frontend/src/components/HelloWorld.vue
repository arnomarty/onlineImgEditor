<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <button class="Search__button" @click="callRestService()">Call Spring Boot REST backend</button>
    <h3>{{ response }}</h3>
  </div>

  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>

  <div id="v-model-select-dynamic" class="demo">
    <select v-model="selected">
      <option v-for="img in response" :key="img.id" :value="img.id">
        {{ img.name }}
      </option>
    </select>
    <span>Selected: {{ selected }}</span>
  </div>

  <div class="column side">
    <ul>
      <li v-for="img in response" :key=img.id>
        <a @click="setSelected(img.id)"><img class="imgs" :src="'images/'+img.id"></a>
      </li>
    </ul>
  </div>

  <div class="column middle">
    {{ imageUrl }}
    <img class="imgs" v-bind:src="imageUrl">
  </div>
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
      imageUrl: 'images/0',
      file: ''
    }
  },
  watch: {
    selected: function () {
      console.log('SELECTED!')
      this.getSpecificPicture()
      this.imageUrl = 'images/' + this.selected
    }
  },
  methods: {
    handleFileUpload () {
      this.file = this.$refs.file.files[0]
    },
    submitFile () {
      const formData = new FormData()
      formData.append('file', this.file)
      axios.post('/images',
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
      ).then(function () {
        console.log('Success!')
        this.callRestService()
      })
        .catch(function () {
          console.log('Failure!')
        })
    },
    setSelected (id) {
      this.selected = id
    },
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
    getSpecificPicture () {
      var link = 'images/' + this.selected
      axios.get(link, { responseType: 'blob' })
        .then(function (response) {
          var reader = new window.FileReader()
          reader.readAsDataURL(response.data)
          reader.onload = function () {
            this.imageUrl = reader.result
            this.imageUrl = link
            console.log(this.imageUrl)
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
html{
  height: 100%;
}

h3 {
  margin: 40px 0 0;
}

.column {
  float: left;
  padding: 10px;
}

.column.side {
  width: 20%;
    height: 90vh;
  border: 1px solid red;
  overflow: scroll;
}

.column.middle {
  width: 70%;
}

.imgs {
  max-width: 100%;
  object-fit: scale-down;
  border: 1px solid black;
}

@media screen and (max-width: 600px) {
  .column.side, .column.middle {
    width: 100%;
  }
}

</style>
