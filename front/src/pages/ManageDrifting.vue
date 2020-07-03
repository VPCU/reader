<template>
  <q-list>
    <q-card inline class="bigger q-ma-sm" v-for="{ driId, bookName, author, curPosition, available, detail } in drifting" :key="driId">
      <q-card-media>
        <img  src="https://cdn.quasar.dev/img/parallax2.jpg" width="100%">
      </q-card-media>
      <div class="bigger q-ma-lg">
        <q-card-title class="relative-position">
          <!-- probably use later
          <q-btn fab color="primary" icon="place" align="right" style="top: 0; right: 8px; transform: translateY(-50%);" />
          -->
          <h6 class="q-my-sm">{{ bookName }} by {{ author }}</h6>
          <div slot="right" class="row items-center">
            <q-icon name="place" /> {{ curPosition }}
          </div>
        </q-card-title>
        <q-card-main>
          <p v-if="available" color:green>可取阅</p>
          <p v-if="!available" color:red>借阅中</p>
          <p class="text-faded">{{ detail }}</p>
        </q-card-main>
        <q-card-separator />
        <q-card-actions class="q-px-none q-px-none" align="center">
          <q-btn flat icon="event" >漂流记录</q-btn>
          <q-btn flat icon="create" @click="editdrift" >编辑</q-btn>
          <q-btn flat icon="highlight_off" color="red" >结束漂流</q-btn>
        </q-card-actions>
      </div>
    </q-card>
  </q-list>
</template>

<script>
export default {
  data () {
    return {
      drifting: []
      // [{ driId: '1', bookName: '书名1', location: 'xxx十字路口xx便利店', state: '借阅中', detail: '书籍介绍或其他信息' }, { driftingid: '2', bookname: '书名2', location: 'xxx小区xx警卫室', state: '闲置', detail: '书籍介绍或其他信息' }]
    }
  },
  mounted: function () {
    this.createcode()// 加载页面时触发的函数
  },
  methods: {
    createcode () {
      this.$axios.get('/drifting/all', {
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data[0]) {
          console.log(response.data)
          this.drifting = response.data
        }
      })
    },
    editdrift (driId) {
      this.$router.push({
        path: '/editdrifting',
        query: { driId: driId }
      })
    }
  }
}
</script>
