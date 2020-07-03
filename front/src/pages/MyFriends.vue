<template>
  <div class="q-pa-md">
    <q-list bordered class="rounded-borders">
      <q-item-label header class="text-h5">我的好友</q-item-label>

      <q-item v-for="contact in contacts" :key="contact.id" class="q-my-sm" clickable v-ripple >
        <q-item-section avatar @click="info">
          <q-avatar>
            <img :src="`${contact.avatar}`">
          </q-avatar>
        </q-item-section>

        <q-item-section @click="info">
          <q-item-label>{{ contact.name }}</q-item-label>
          <q-item-label caption lines="1">{{ contact.email }}</q-item-label>
        </q-item-section>

        <q-item-section side>
          <q-icon name="chat_bubble" color="green" />
        </q-item-section>
      </q-item>
    </q-list>
  </div>
</template>

<script>
const contacts = [{
  id: 1,
  name: 'Ruddy Jedrzej',
  email: 'rjedrzej0@discuz.net',
  avatar: 'https://cdn.quasar.dev/img/avatar1.jpg'
}, {
  id: 2,
  name: 'Mallorie Alessandrini',
  email: 'malessandrini1@marketwatch.com',
  avatar: 'https://cdn.quasar.dev/img/avatar2.jpg'
}, {
  id: 3,
  name: 'Elisabetta Wicklen',
  email: 'ewicklen2@microsoft.com',
  avatar: 'https://cdn.quasar.dev/img/avatar3.jpg'
}, {
  id: 4,
  name: 'Seka Fawdrey',
  email: 'sfawdrey3@wired.com',
  avatar: 'https://cdn.quasar.dev/img/avatar4.jpg'
}, {
  id: 5,
  name: 'Superman',
  email: 'superman@qq.com',
  avatar: 'https://cdn.quasar.dev/img/avatar5.jpg'
}, {
  id: 6,
  name: 'Edward',
  email: 'edward@qq.com',
  avatar: 'https://cdn.quasar.dev/img/avatar6.jpg'
}, {
  id: 7,
  name: '赵钱孙李',
  email: 'zqsl@qq.com',
  avatar: 'https://cdn.quasar.dev/img/boy-avatar.png'
}, {
  id: 8,
  name: '周吴郑王',
  email: 'zwzw@qq.com',
  avatar: 'https://cdn.quasar.dev/img/boy-avatar.png'
}]

export default {
  data () {
    return {
      contacts
    }
  },
  methods: {
    info () {
      this.$router.push('/friendinfo')
    }
  }
}
</script>
