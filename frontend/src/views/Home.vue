<template>
  <div class="home">
    <ImageIO :url="this.imageUrl"/>
    <Gallery msg="Welcome to Your Vue.js App" @updateUrl="updateUrl($event)"/>
  </div>
</template>

<script>
// @ is an alias to /src
import Gallery from '@/components/Gallery.vue'
import ImageIO from '@/components/ImageIO.vue'

export default {
  name: 'Home',
  components: {
    Gallery,
    ImageIO
  },
  data () {
    return {
      imageUrl: 'images/0'
    }
  },
  methods: {
    updateUrl (url) {
      this.imageUrl = url
      console.log("'Passed:'+this.imageUrl")
    }
  }
}
</script>
