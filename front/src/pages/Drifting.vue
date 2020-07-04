<template>
  <q-infinite-scroll @load="onLoad" ref="infiniteScroll" :offset="250">
    <q-card inline class="bigger q-ma-sm" v-for="{ driId, bookName, author, curPosition, available, detail, guard} in drifting" :key="driId">
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
          <div slot="right" class="row items-center" v-if="guard">
            <q-icon name="person_pin" />保管人:{{ guard }}
          </div>
        </q-card-title>
        <q-card-main>
          <p v-if="available" color:green>可取阅</p>
          <p v-if="!available" color:red>借阅中</p>
          <p class="text-faded">{{ detail }}</p>
        </q-card-main>
        <q-card-separator />
        <q-card-actions class="q-px-none q-px-none" align="center">
          <q-btn flat icon="local_library" color="green" v-if="available" @click="borrow(driId)">我已取阅</q-btn>
          <q-btn flat icon="where_to_vote" color="blue" v-if="!available" @click="retureb(driId)">我已归还</q-btn>
        </q-card-actions>
      </div>
    </q-card>
  </q-infinite-scroll>
</template>

<script>
export default {
  data () {
    return {
      offset: 1,

      drifting: []
      // [{ driId: '1', bookName: '书名1', location: 'xxx十字路口xx便利店', state: '借阅中', detail: '书籍介绍或其他信息' }, { driftingid: '2', bookname: '书名2', location: 'xxx小区xx警卫室', state: '闲置', detail: '书籍介绍或其他信息' }]
    }
  },
  mounted: function () {
    this.createcode()// 加载页面时触发的函数
  },
  methods: {
    onLoad () {
      this.$axios.get('/drifting/bylimit', {
        params: {
          cursor: this.$data.offset,
          limit: 5,
          desc: false
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        console.log(response.data)
        if (response.data.length) {
          var data = response.data
          this.drifting = response.data
          data.forEach(e => this.items.push(e))
          this.offset = data[data.length - 1].driId + 1
        } else {
          this.$refs.infiniteScroll.stop()
          console.log('stop')
        }
      })
    },
    editdrift (driId) {
      this.$router.push({
        path: '/editdrifting',
        query: { driId: driId }
      })
    },
    borrow (driId) {
      this.$axios.get('/drifting/borrow', {
        params: {
          driftId: driId
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        console.log(response.data)
        if (response.data.succ) {
          this.$router.push('/drifting')
          // this.drifting = response.data
        }
      })
    },
    returnb (driId) {
      this.$axios.get('/drifting/borrow', {
        params: {
          driftId: driId
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        console.log(response.data)
        if (response.data.succ) {
          this.$router.push('/drifting')
          // this.drifting = response.data
        }
      })
    }
  }
}
</script>
