<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
import 'boot/store'
export default {
  name: 'App',
  created () {
    this.$gStore.token = localStorage.token
  }
}
</script>
