<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
import 'boot/store'
import 'boot/axios'
export default {
  name: 'App',
  created () {
    if (localStorage.token) {
      this.$gStore.token = localStorage.token
      this.$axios.get('getuser', {
        params: {
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        this.$gStore.user = response.data
      })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>
