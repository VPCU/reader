<template xmlns:max-width="http://www.w3.org/1999/xhtml">
  <div class="q-pa-md">
    <q-infinite-scroll @load="onLoad" :offset="250" ref="infiniteScroll">
      <div v-for="(item, index) in items" :key="index" class="caption">
        <template>
          <q-card flat bordered class="my-card bg-grey-1">
            <!--            这里控制用户头像和用户名-->
            <q-item>
              <q-item-section avatar>
                <q-avatar>
                  <img :src="item.userImgSrc">
                </q-avatar>
              </q-item-section>

              <q-item-section>
                <q-item-label>用户名</q-item-label>
                <q-item-label caption>
                  用户简介
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-separator />
            <!--            这里控制该书评的内容-->
            <q-card-section horizontal @click="readdetail(item.rid)">

              <q-card-section class="q-pt-xs">
                <div class="text-overline">Overline</div>
                <div class="text-h5 q-mt-sm q-mb-xs">{{String(item.title)}}</div>
                <div class="text-caption text-grey">
                  <p v-html="$options.filters.ellipsis(String(item.content))"></p>
                </div>
              </q-card-section>
            </q-card-section>

            <q-card-section>
            </q-card-section>

            <q-card-actions align="right">
              <q-btn flat round color="red" icon="favorite">
                <p>
                  {{item.ekil}}
                </p>
              </q-btn>
            </q-card-actions>
            <q-separator />
          </q-card>
        </template>
      </div>
      <template v-slot:loading>
        <div class="row justify-center q-my-md">
          <q-spinner-dots color="primary" size="40px" />
        </div>
      </template>
      <q-page-scroller position="bottom-left" :scroll-offset="150" :offset="[18, 18]">
        <q-btn fab icon="keyboard_arrow_up" color="grey-3" />
      </q-page-scroller>
    </q-infinite-scroll>
  </div>

</template>
<!--从后端获取到各种信息-->
<script>
import 'boot/axios'
import 'boot/store'
export default {
  filters: {
    // 当渲染的文字超出30字后显示省略号
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 30) {
        return value.slice(0, 30) + '...'
      }
      return value
    },
    ekil (rid) {
      this.getekil(rid)
    }
  },
  data () {
    return {
      items: [],
      errmsg: '',
      prompt: false,
      alert: false,
      reports: ''
    }
  },
  methods: {
    updateEkil (e) {
      this.$axios.get('reviews/countekil', {
        params: {
          rid: e.rid
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        e.ekil = response.data
      })
        .catch((error) => {
          console.log(error)
        })
    },
    onLoad (index, done) {
      this.$axios.get('reviews/my', {
        params: {
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(response.data)
        if (response.data.length) {
          var data = response.data
          data.forEach(e => {
            e.ekil = 0 // required
            this.updateEkil(e)
          })
          data.forEach(e => this.items.push(e))
          this.$refs.infiniteScroll.stop()
        } else {
          this.$refs.infiniteScroll.stop()
          console.log('stop')
        }
        done()
      })
        .catch((error) => {
          console.log(error)
        })

      // setTimeout(() => {
      //   if (this.items) {
      //     this.items.splice(0, 0, {}, {}, {}, {}, {}, {}, {})
      //     done()
      //   }
      // }, 2000)
    },
    readdetail (rid) {
      this.$router.push({
        path: '/readreview',
        query: { rid: String(rid) }
      })
    },
    getekil (rid) {
      this.$axios.get('reviews/countekil', {
        params: {
          rid: rid
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        if (response.data[0]) {
          console.log('调用了ekil' + response.data[0])
          return response.data[0]
        }
      })
        .catch((error) => {
          console.log(error)
        })
    },
    setekil (rid, like, index) {
      this.$axios.get('reviews/setlike', {
        params: {
          rid: rid,
          like: like
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        if (!response.data.succ) {
          this.$data.errmsg = response.data.msg
        } else {
          console.log('点赞成功')
          this.ekil[index]++
        }
      })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>
<!--样式-->
<style lang="sass" scoped>
  .my-card
    width: 100%
    max-width: 100%
</style>
