<template>
  <q-page class="flex flex-center">
    <div class="row justify-center">
    <img
      alt="Quasar logo"
      src="~assets/quasar-logo-full.svg"
    >
      <div style="margin-top: 20px"><q-chip icon="event">{{count}}</q-chip></div>
    </div>
  </q-page>
</template>

<script>
import 'boot/axios'
export default {
  name: 'PageIndex',
  data () {
    return {
      count: 0
    }
  },
  methods: {
    loadData () {
      this.$axios.get('count').then((response) => {
        console.log(response)
        this.count = response.data.count
      })
    }
  },
  created () {
    this.loadData()
  }
}
</script>
