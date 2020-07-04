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

    <q-input v-model="username" placeholder="用户名"  style="margin-left: 7%; margin-right: 7%;">
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
      <q-btn outline rounded color="primary" label="保存修改" />
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      url: 'https://cdn.quasar.dev/img/boy-avatar.png',
      uid: this.$route.query.uid
    }
  },
  methods: {
    refresh () {
      this.url = 'https://placeimg.com/500/300/animals?t=' + Math.random()
    }
  }
}
</script>

<style scoped>

</style>
