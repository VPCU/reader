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
      <span>点击更换头像</span>
    </div>

    <q-input v-model="username" placeholder="用户名" readonly style="margin-left: 7%; margin-right: 7%">
      <template v-slot:prepend>
        <q-icon name="person_pin" />
      </template>
    </q-input>

    <q-input v-model="introduction" placeholder="用户简介"  style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="event_note" />
      </template>
    </q-input>

    <q-input v-model="nick" placeholder="昵称"  style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="face" />
      </template>
    </q-input>

    <q-input v-model="email" placeholder="邮箱"  style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="mail_outline" />
      </template>
    </q-input>

    <q-input v-model="phone" placeholder="手机号"  style="margin-left: 7%; margin-right: 7%;">
      <template v-slot:prepend>
        <q-icon name="smartphone" />
      </template>
    </q-input>

    <div class="row justify-center">
    <q-btn outline rounded color="primary" label="保存修改" @click="submit" />
    </div>
  </div>
</template>

<script>
import 'boot/axios'
import 'boot/store'
export default {
  data () {
    return {
      url: this.$gStore.user.imgSrc,
      username: this.$gStore.user.username,
      introduction: this.$gStore.user.resume,
      nick: this.$gStore.user.nick,
      email: this.$gStore.user.email,
      phone: this.$gStore.user.phone
    }
  },
  methods: {
    refresh () {
      console.log(this.$gStore.user)
      this.url = 'https://placeimg.com/500/300/animals?t=' + Math.random()
    },
    submit () {
      this.$axios.post('user/edit', {
        userResume: this.introduction,
        userNick: this.nick,
        userEmail: this.email,
        phone: this.phone,
        imgSrc: this.url
      }, {
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(this.$gStore.token)
        console.log(response.data)
        if (response.data.succ) {
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
          this.$router.back()
        }
      })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>

</style>
