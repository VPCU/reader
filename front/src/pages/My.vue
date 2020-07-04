<template>
  <div class="q-pa-md q-gutter-y-md">
    <q-item>
      <q-item-section side>
        <q-avatar rounded size="48px">
          <img src="https://cdn.quasar.dev/img/boy-avatar.png" />
        </q-avatar>
      </q-item-section>
      <q-item-section>
        <q-item-label>{{ nickname }}</q-item-label>
        <q-item-label caption>{{ role }}</q-item-label>
      </q-item-section>
      <q-item-section side>
        <q-icon name="settings" clickable v-ripple size="28px" @click="set"/>
      </q-item-section>
    </q-item>

    <q-separator spaced />

    <div class="q-col-gutter-md row items-start">
      <div class="col-4">
        <q-img
          src="https://s1.ax1x.com/2020/07/05/NzXAYR.jpg"
          style="width: 100%" clickable v-ripple
          class="rounded-borders"
          @click="like"
        >
          <div class="absolute-full text-subtitle2 flex flex-center">
                     收藏
          </div>
        </q-img>
      </div>
      <div class="col-4">
        <q-img
          src="https://s1.ax1x.com/2020/07/05/NzXKmD.jpg"
          style="width: 100%" clickable v-ripple
          class="rounded-borders"
          @click="friend"
        >
          <div class="absolute-full text-subtitle2 flex flex-center">
                     好友
          </div>
        </q-img>
      </div>
      <div class="col-4">
        <q-img
          src="https://s1.ax1x.com/2020/07/05/NzX1kd.jpg"
          style="width: 100%" clickable v-ripple
          class="rounded-borders"
          @click="message"
        >
          <div class="absolute-full text-subtitle2 flex flex-center">
                     私信
          </div>
        </q-img>
      </div>
    </div>

    <q-card class="my-card">
      <q-card-section class="bg-light-blue-3 text-white">
        <div class="text-h6">我的书评</div>
      </q-card-section>

      <q-card-actions align="around">
        <q-btn flat icon="bookmark" color="blue-grey-3" @click="myreview"> <span class="text-black">查看全部</span> </q-btn>
        <q-btn flat icon="create" color="blue-grey-3" @click="add"> <span class="text-black">写书评</span> </q-btn>
      </q-card-actions>
    </q-card>

    <q-card class="my-card">
      <q-card-section class="bg-blue-3 text-white">
        <div class="text-h6">我的漂流</div>
      </q-card-section>

      <q-card-actions align="around">
        <q-btn flat icon="timeline" color="blue-grey-3" @click="managedrift"> <span class="text-black">管理漂流</span> </q-btn>
        <q-btn flat icon="create" color="blue-grey-3" @click="newdrift"> <span class="text-black">发起漂流</span> </q-btn>
      </q-card-actions>
    </q-card>

    <q-card class="my-card" v-if="admin">
      <q-card-section class="bg-orange text-white">
        <div class="text-h6">后台管理</div>
      </q-card-section>

      <q-card-actions align="around">
        <q-btn flat icon="info" color="blue-grey-3" @click="managereport"> <span class="text-black">举报处理</span> </q-btn>
        <q-btn flat icon="create" color="blue-grey-3" @click="todo"> <span class="text-black">用户管理</span> </q-btn>
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      admin: this.$gStore.user.roles.includes('admin'),
      nickname: this.$gStore.user.nick,
      role: this.$gStore.user.roles[0]
    }
  },
  methods: {
    add () {
      this.$router.push('/newreview')
    },
    myreview () {
      this.$router.push('/readmyreview')
    },
    newdrift () {
      this.$router.push('/newdrifting')
    },
    managedrift () {
      this.$router.push('/managedrifting')
    },
    set () {
      this.$router.push('/mysettings')
    },
    friend () {
      this.$router.push('/myfriends')
    },
    like () {
      this.$router.push('/mylike')
    },
    managereport () {
      this.$router.push('/managereport')
    },
    message () {
      this.$router.push('/mymessages')
    }
  }
}
</script>

<style lang="sass" scoped>

</style>
