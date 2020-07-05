<template>
  <div class="q-pa-md q-gutter-y-md">
    <div class="column items-center">
        <q-item-section avatar>
          <q-avatar size="100px">
            <img :src="this.user.imgSrc">
          </q-avatar>
        </q-item-section>
        <span>{{name}}</span>
    </div>

    <q-list bordered separator style="margin-left: 2%; margin-right: 2%;">
      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>昵称</q-item-label>
          <q-item-label>{{this.user.nick}}</q-item-label>
        </q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>简介</q-item-label>
          <q-item-label>{{this.user.resume}}</q-item-label>
        </q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>邮箱</q-item-label>
          <q-item-label>{{this.user.email}}</q-item-label>
        </q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>手机</q-item-label>
          <q-item-label>{{this.user.phone}}</q-item-label>
        </q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>他的书评</q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>他的漂流</q-item-section>
      </q-item>
    </q-list>

    <div class="column items-center" >
    <q-btn outline rounded color="primary" label="发私信" style="margin-bottom: 3%;" icon="chat" @click="sendmessage"/>
    <q-btn outline rounded color="primary" label="取消关注" icon="person_remove" @click="delfriend"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      url: 'https://cdn.quasar.dev/img/avatar1.jpg',
      user: null
    }
  },
  methods: {
    sendmessage () {
      this.$router.push({
        path: '/MessageDetail',
        query: { uid: this.$route.query.uid }
      })
    },
    delfriend () {
      this.$axios.get('friends/del', {
        params: {
          uid: this.$route.query.uid
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(response.data)
        this.$router.back()
      })
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
    })
  }
}
</script>
