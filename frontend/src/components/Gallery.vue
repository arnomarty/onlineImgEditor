<template>
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
  name: 'Gallery',
  emits: ['updateUrl'],
  props: {
    msg: Boolean
  },
  data () {
    return {
      response: [],
      errors: [],
      selected: '0',
      imageUrl: 'images/0'
    }
  },
  watch: {
    selected: function () {
      this.getSpecificPicture()
      this.imageUrl = 'images/' + this.selected
      this.$emit('updateUrl', this.imageUrl)
    },
    msg: function () {
      this.callRestService()
      console.log('Received!')
      this.selected = this.response[0].id
    }
  },
  methods: {
    setSelected (id) {
      this.selected = id
    },

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

.column {
  float: left;
  margin-top: 10px;
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
  border: 1px solid blue;
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
