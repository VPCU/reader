<template>
  <q-list>
    <q-card inline class="bigger q-ma-sm" v-for="{ driftingid, bookname, location, state, detail } in drifting" :key="driftingid">
      <q-card-media>
        <img  src="https://cdn.quasar.dev/img/parallax2.jpg" width="100%">
      </q-card-media>
      <div class="bigger q-ma-md">
        <q-card-title class="relative-position">
          <!-- probably use later
          <q-btn fab color="primary" icon="place" align="right" style="top: 0; right: 8px; transform: translateY(-50%);" />
          -->
          <div class="ellipsis">{{ bookname }}</div>
          <div slot="right" class="row items-center">
            <q-icon name="place" /> {{ location }}
          </div>
        </q-card-title>
        <q-card-main>
          <p>{{ state }}</p>
          <p class="text-faded">{{ detail }}</p>
        </q-card-main>
        <q-card-separator />
        <q-card-actions class="q-mx-none">
          <q-btn flat icon="event" >漂流记录</q-btn>
          <q-btn flat icon="create" >编辑</q-btn>
          <q-btn flat color="red" >结束漂流</q-btn>
        </q-card-actions>
      </div>
    </q-card>
  </q-list>
</template>

<script>
export default {
  data () {
    return {
      drifting: [{ driftingid: '1', bookname: '书名1', location: 'xxx十字路口xx便利店', state: '借阅中', detail: '书籍介绍或其他信息' }, { driftingid: '2', bookname: '书名2', location: 'xxx小区xx警卫室', state: '闲置', detail: '书籍介绍或其他信息' }]
    }
  }
}
</script>
