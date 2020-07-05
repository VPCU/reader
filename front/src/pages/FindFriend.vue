<template>
  <div class="q-pa-md q-gutter-y-md">
    <div class="column items-center">
      <q-img
        :src="url"
        spinner-color="white"
        style="height: 140px; max-width: 150px"
        class="rounded-borders"
        @click="refresh"
      />
    </div>

    <q-input v-model="username" placeholder="用户名" readonly style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="person_pin" />
      </template>
    </q-input>

    <q-input v-model="introduction" placeholder="用户简介" readonly style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="event_note" />
      </template>
    </q-input>

    <q-input v-model="nick" placeholder="昵称" readonly style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="face" />
      </template>
    </q-input>

    <q-input v-model="email" placeholder="邮箱" readonly style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="mail_outline" />
      </template>
    </q-input>

    <q-input v-model="phone" placeholder="手机号" readonly style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="smartphone" />
      </template>
    </q-input>

    <div class="row justify-center">
      <q-btn outline rounded color="primary" label="添加好友" @click="addfriend" />
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      url: 'https://cdn.quasar.dev/img/boy-avatar.png',
      username: '',
      event_note: '',
      introduction: '',
      nick: '',
      email: '',
      phone: '',
      user: null
    }
  },
  methods: {
    addfriend () {
      this.$axios.get('friends/add', {
        params: {
          uid: this.$route.query.uid
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(response.data)
      })
    },
    refresh () {
      this.url = 'https://placeimg.com/500/300/animals?t=' + Math.random()
    }
  },
  created () {
    console.log(this.$route.query.uid)
    this.$axios.get('friends/find', {
      params: {
        uid: this.$route.query.uid
      },
      headers: {
        token: this.$gStore.token
      }
    }).then((response) => {
      if (response.data.code === 4401) this.$router.push('/login')
      console.log(response.data)
      this.user = response.data
      this.username = this.user.uname
      this.introduction = this.user.resume
      this.nick = this.user.nick
      this.email = this.user.email
      this.phone = this.user.phone
    })
  }
}
</script>

<style scoped>

</style>
