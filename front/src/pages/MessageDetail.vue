<template>
  <div class="q-pa-md row justify-center">
    <div style="width: 100%;" v-for="detail in details" :key="detail.name">
      <q-chat-message v-if=" detail.isMe == false "
      name="me"
      :avatar="`${detail.user.imgSrc}`"
      :text="[`${detail.message.msg}`]"
      :stamp="`${detail.message.time}`"
      sent
      />
      <q-chat-message v-else
      :name="`${detail.user.nick}`"
      :avatar="`${detail.user.imgSrc}`"
      :text="[`${detail.message.msg}`]"
      :stamp="`${detail.message.time}`"
      />
    </div>

    <q-page-sticky position="bottom" :offset="[0, 15]">
      <q-input standout="bg-grey-6" v-model="text" style="width:325px">
        <template v-slot:append>
          <q-btn round dense flat icon="send" @click="send"/>
        </template>
      </q-input>
    </q-page-sticky>

  </div>
</template>

<script>
/* const details = [{
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
}] */
export default {
  data () {
    return {
      details: [],
      text: ''
    }
  },
  methods: {
    send () {
      this.$axios.post('message/send', {
        receiverId: this.$route.query.uid,
        content: this.text
      }, {
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(response.data)
        this.$router.back()
      })
        .catch((error) => {
          console.log(error)
        })
    }
  },
  created () {
    this.$axios.get('/message/allsent', {
      headers: {
        token: this.$gStore.token
      }
    }).then((response) => {
      if (response.data.code === 4401) this.$router.push('/login')
      console.log(response.data)
      var data = response.data
      data.forEach(e => {
        console.log(e.message)
        if (String(e.message.rvId) === String(this.$route.query.uid)) {
          this.details.push(e)
        }
      })
    })
  }
}
</script>
