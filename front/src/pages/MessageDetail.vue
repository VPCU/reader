<template>
  <div class="q-pa-md row justify-center">
    <div style="width: 100%; max-width: 400px" v-for="detail in details" :key="detail.name">
      <q-chat-message v-if=" detail.isMe == true "
      name="me"
      :avatar="`${detail.url}`"
      :text="[`${detail.content}`]"
      :stamp="`${detail.time}`"
      sent
      />
      <q-chat-message v-else
      :name="`${detail.name}`"
      :avatar="`${detail.url}`"
      :text="[`${detail.content}`]"
      :stamp="`${detail.time}`"
      />
    </div>
  </div>
</template>

<script>
const details = [{
  name: 'user0',
  content: '你好！！！！！',
  time: '11:00',
  url: 'https://cdn.quasar.dev/img/boy-avatar.png',
  isMe: true
}, {
  name: 'Ruddy Jedrzej',
  content: '你好',
  time: '11:20',
  url: 'https://cdn.quasar.dev/img/avatar1.jpg',
  isMe: false
}]
export default {
  data () {
    return {
      details
    }
  },
  methods: {
  }
}
</script>
