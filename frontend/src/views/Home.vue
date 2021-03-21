<template>
  <div class="home">
    <AlgorithmPanel />
    <ImageIO :url="this.imageUrl" @reload="updateImgList($event)"/>
    <Gallery :msg="this.msg" @updateUrl="updateUrl($event)"/>
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
      imageUrl: 'images/0',
      msg: false
    }
  },
  methods: {
    updateUrl (url) {
      this.imageUrl = url
      console.log("'Passed:'+this.imageUrl")
    },
    updateImgList (status) {
      console.log('IN TRANSIT!')
      this.msg = status
    }
  }
}
</script>
