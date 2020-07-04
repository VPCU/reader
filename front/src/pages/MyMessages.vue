<template>
  <div class="q-pa-md">
    <q-item clickable v-ripple v-for="contact in messages" :key="contact.name">
      <q-item-section side>
        <q-avatar rounded size="48px" @click="detail">
          <img :src="`${contact.url}`" />
        </q-avatar>
      </q-item-section>

      <q-item-section @click="detail">
        <q-item-label>{{contact.name}}</q-item-label>
        <q-item-label caption>{{contact.last_message}}</q-item-label>
      </q-item-section>

      <q-item-section side>
        {{contact.last_time}}
        <q-icon name="delete" @click="delet"/>
      </q-item-section>
    </q-item>
  </div>
</template>

<script>
const messages = [{
  name: 'Ruddy Jedrzej',
  last_message: '你好',
  last_time: '11:20',
  url: 'https://cdn.quasar.dev/img/avatar1.jpg'
}, {
  name: 'Seka Fawdrey',
  last_message: '再见',
  last_time: '10:30',
  url: 'https://cdn.quasar.dev/img/avatar4.jpg'
}]
export default {
  data () {
    return {
      messages
    }
  },
  methods: {
    delet () {

    },
    detail () {
      this.$router.push('/messagedetail')
    }
  }
}
</script>
