<template>
  <q-page class="flex flex-center">
    <div class="q-gutter-md" style="max-width: 600px">
      <q-input outlined v-model="username" label="用户名" />
      <q-input outlined v-model="phone" label="手机号码" />
      <q-input outlined v-model="nick" label="昵称" />
      <q-input outlined v-model="email" label="邮箱" />
      <q-input outlined v-model="password" label="密码" />
      <q-input outlined v-model="confirm_password" label="确认密码" />
      <div class="row justify-between">
        <div class="col-5">
          <q-btn color="white" text-color="black" @click="login"  label="登录" style="width: 100%" />
        </div>
        <div class="col-5">
          <q-btn color="white" text-color="black" @click="signup" label="注册" style="width: 100%" />
        </div>
      </div>

    </div>

    <q-dialog v-model="alert">
      <q-card>
        <q-card-section>
          <div class="text-h6">注册失败</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{errmsg}}
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="OK" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>

  </q-page>
</template>

<script>
import 'boot/axios'
export default {
  name: 'Login',
  data () {
    return {
      username: '',
      password: '',
      confirm_password: '',
      phone: '',
      alert: false,
      errmsg: '',
      nick: '',
      email: ''
    }
  },
  methods: {
    signup () {
      this.$axios.post('signup', {
        username: this.$data.username,
        password: this.$data.password,
        nick: this.$data.nick,
        email: this.$data.email,
        phone: this.$data.phone
      })
        .then((response) => {
          console.log(response.data)
          if (!response.data.succ) {
            this.$data.errmsg = response.data.oper
            this.$data.alert = true
          } else {
            console.log('注册成功')
          }
        })
        .catch(() => {
          alert('error')
        })
    }
  }
}
</script>
