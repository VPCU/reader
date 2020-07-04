<template>
  <div @keyup.enter="login">
    <div class="q-ma-xl q-pt-lg" align="center">
      <p style="font-size: 50px" class="q-ma-none"><q-icon name="menu_book" style="font-size: 65px" />Reader</p>
    </div>
    <div class="q-mt-lg q-pt-lg">
      <q-input outlined class="q-ma-md" v-model="username" label="用户名" />
      <q-input type="password" outlined class="q-ma-md" v-model="password" label="密码" />
      <div class="row justify-between q-mx-md q-px-none">
        <div class="col-6">
          <q-btn outline color="primary" @click="login" label="登录" style="width: 100%" />
        </div>
        <div class="col-5">
          <q-btn outline color="primary" @click="signup" label="注册" style="width: 100%" />
        </div>
      </div>
    </div>

    <q-dialog v-model="alert">
      <q-card>
        <q-card-section>
          <div class="text-h6">登录失败</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{errmsg}}
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="OK" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import 'boot/axios'
import 'boot/store'
export default {
  name: 'Login',
  data () {
    return {
      username: '',
      password: '',
      alert: false,
      errmsg: ''
    }
  },
  methods: {
    login () {
      this.$axios.post('login', {
        username: this.$data.username,
        password: this.$data.password
      })
        .then((response) => {
          console.log(response.data)
          if (!response.data.succ) {
            this.$data.errmsg = response.data.msg
            this.$data.alert = true
          } else {
            console.log('登录成功')
            this.$gStore.token = response.data.token
            this.$gStore.user = response.data
            localStorage.token = response.data.token
            this.$router.push('/')
          }
        })
        .catch(() => {
          alert('error')
        })
    },
    signup () {
      this.$router.push('/signup')
    }
  }
}
</script>
