<template>
  <iframe :src="src" width=100% height=455px ></iframe>
</template>

<script>
export default {
  name: 'mall',
  data () {
    return {
      src: 'http://localhost:8082/#/'
    }
  }
}
</script>

<style scoped>

</style>
