<template>
  <div class="q-pa-md row justify-center">
    <div style="width: 100%;" v-for="detail in details" :key="detail.name">
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

    <q-page-sticky position="bottom" :offset="[0, 15]">
      <q-input standout="bg-grey-6" v-model="text" style="width:325px">
        <template v-slot:append>
          <q-btn round dense flat icon="send" />
        </template>
      </q-input>
    </q-page-sticky>

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
}, {
  name: 'Ruddy Jedrzej',
  content: '你好',
  time: '11:20',
  url: 'https://cdn.quasar.dev/img/avatar1.jpg',
  isMe: false
}, {
  name: 'Ruddy Jedrzej',
  content: '你好',
  time: '11:20',
  url: 'https://cdn.quasar.dev/img/avatar1.jpg',
  isMe: false
}, {
  name: 'Ruddy Jedrzej',
  content: '你好',
  time: '11:20',
  url: 'https://cdn.quasar.dev/img/avatar1.jpg',
  isMe: false
}, {
  name: 'Ruddy Jedrzej',
  content: '你好',
  time: '11:20',
  url: 'https://cdn.quasar.dev/img/avatar1.jpg',
  isMe: false
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
