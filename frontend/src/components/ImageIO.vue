<template>
  <div class="uploader">
    <div class="downloader">
      <button @click="downloadPicture()">Download</button>
    </div>
    <div class="downloader">
      <button @click="deletePicture()">Delete</button>
    </div>
    <div>
      <button v-on:click="toggleUploadButton()">Upload</button>
    </div>
    <div  v-if="toggleUpload == true">
      <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ImageIO',
  emits: ['reload'],
  props: {
    url: String
  },

  data () {
    return {
      response: [],
      errors: [],
      file: '',
      imageUrl: 'images/0',
      toggleUpload: false,
      reload: false
    }
  },

  watch: {
    url: function () {
      this.imageUrl = this.url
    }
  },

  methods: {
    downloadPicture () {
      console.log('Passed=')
      console.log(this.imageUrl)
      axios({
        url: this.imageUrl,
        method: 'GET',
        responseType: 'blob'
      }).then((response) => {
        var fileUrl = window.URL.createObjectURL(new Blob([response.data]))
        var fileLink = document.createElement('a')
        fileLink.href = fileUrl
        fileLink.setAttribute('download', 'file.jpg')
        document.body.appendChild(fileLink)
        fileLink.click()
      })
    },

    deletePicture () {
      axios
        .delete(this.imageUrl)
        .then(function (response) {
          console.log(response)
        })
        .catch(function (error) {
          console.log(error)
        })
      this.reload = !this.reload
      this.$emit('reload', this.reload)
    },

    toggleUploadButton () {
      this.toggleUpload = !this.toggleUpload
    },

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
      })
        .catch(function () {
          console.log('Failure!')
        })
      this.reload = !this.reload
      this.$emit('reload', this.reload)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.uploader {
    background: cyan;
    float: left;
    display: inline-flex;
    border: 1px solid green;
}

.downloader {
  background: red;
  padding-right: 5px;
}

</style>
