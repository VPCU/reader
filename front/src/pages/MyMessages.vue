<template>
  <div class="q-pa-md">
    <q-item clickable v-ripple v-for="contact in messages" :key="contact.name">
      <q-item-section side>
        <q-avatar rounded size="48px">
          <img :src="`${contact.user.imgSrc}`" />
        </q-avatar>
      </q-item-section>

      <q-item-section>
        <q-item-label>{{contact.user.nick}}</q-item-label>
        <q-item-label caption>{{contact.message.msg}}</q-item-label>
      </q-item-section>

      <q-item-section side>
        {{contact.last_time}}
        <!--<q-icon name="delete" @click="delet"/>-->
      </q-item-section>
    </q-item>
  </div>
</template>

<script>
/* const messages = [{
  name: 'Ruddy Jedrzej',
  last_message: '你好',
  last_time: '11:20',
  url: 'https://cdn.quasar.dev/img/avatar1.jpg'
}, {
  name: 'Seka Fawdrey',
  last_message: '再见',
  last_time: '10:30',
  url: 'https://cdn.quasar.dev/img/avatar4.jpg'
}] */
export default {
  data () {
    return {
      messages: null
    }
  },
  methods: {
    delet () {

    },
    detail () {
      this.$router.push('/messagedetail')
    }
  },
  created () {
    this.$axios.get('/message/all', {
      headers: {
        token: this.$gStore.token
      }
    }).then((response) => {
      if (response.data.code === 4401) this.$router.push('/login')
      console.log(response.data)
      this.messages = response.data
    })
  }
}
</script>
