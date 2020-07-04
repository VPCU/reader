<template>
  <iframe :src="src" width=100% height=455px ></iframe>
</template>

<script>
export default {
  name: 'mall',
  data () {
    return {
      src: 'http://127.0.0.1:8080/#/'
    }
  }
}
</script>

<style scoped>

</style>
