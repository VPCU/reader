<template>
  <div class="q-pa-md q-gutter-y-md">
    <div class="column items-center">
        <q-item-section avatar>
          <q-avatar size="100px">
            <img :src="url">
          </q-avatar>
        </q-item-section>
        <span>{{name}}</span>
    </div>

    <q-list bordered separator style="margin-left: 2%; margin-right: 2%;">
      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>昵称</q-item-label>
          <q-item-label>我是Ruddy</q-item-label>
        </q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>简介</q-item-label>
          <q-item-label>hello!</q-item-label>
        </q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>邮箱</q-item-label>
          <q-item-label>{{email}}</q-item-label>
        </q-item-section>
      </q-item>

      <q-item clickable v-ripple>
        <q-item-section>
          <q-item-label caption>手机</q-item-label>
          <q-item-label>2333</q-item-label>
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
    <q-btn outline rounded color="primary" label="发私信" style="margin-bottom: 3%;" icon="chat"/>
    <q-btn outline rounded color="primary" label="取消关注" icon="person_remove"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      name: 'Ruddy Jedrzej',
      email: 'rjedrzej0@discuz.net',
      url: 'https://cdn.quasar.dev/img/avatar1.jpg'
    }
  },
  methods: {

  }
}
</script>
