<template>
  <div class="home">
    <AlgorithmPanel />
    <ImageIO :url="this.imageUrl"/>
    <Gallery msg="Welcome to Your Vue.js App" @updateUrl="updateUrl($event)"/>
  </div>
</template>

<script>
// @ is an alias to /src
import Gallery from '@/components/Gallery.vue'
import ImageIO from '@/components/ImageIO.vue'
import AlgorithmPanel from '@/components/AlgorithmPanel.vue'

export default {
  name: 'Home',
  components: {
    Gallery,
    ImageIO,
    AlgorithmPanel
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
