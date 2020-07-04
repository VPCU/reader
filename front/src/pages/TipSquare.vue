<template xmlns:max-width="http://www.w3.org/1999/xhtml">
  <div class="q-pa-md">
    <q-infinite-scroll @load="onLoad" ref="infiniteScroll" :offset="250">
      <div v-for="(item, index) in items" :key="index" class="caption">
        <template>
          <q-card flat bordered class="my-card bg-grey-1">
<!--            这里控制用户头像和用户名-->
            <q-item>
              <q-item-section avatar>
                <q-avatar>
                  <img src="https://cdn.quasar.dev/img/boy-avatar.png">
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

            <q-separator />

            <q-card-actions align="right">
              <q-btn flat round color="red" icon="favorite" @click = "setekil(item.rid,!item.liked, item)">
                <p>
                  {{item.ekil}}
                </p>
              </q-btn>
              <q-btn flat round color="teal" icon="bookmark" >收藏</q-btn>
              <q-btn flat round color="primary" @click="prompt = true"  icon="share" >举报</q-btn>
              <q-dialog v-model="prompt" persistent>
                <q-card style="max-width: 300px">
                  <q-card-section>
                    <div class="text-h6">请输入举报理由</div>
                  </q-card-section>

                  <q-card-section class="q-pt-none">
                    <q-input type="textarea" dense v-model="reports" autofocus @keyup.enter="prompt = false" />
                  </q-card-section>

                  <q-card-actions align="right" class="text-primary">
                    <q-btn flat label="取消" v-close-popup />
                    <q-btn flat label="确认举报" @click="reportto(item.rid)" v-close-popup />
                  </q-card-actions>
                </q-card>
              </q-dialog>
              <q-dialog v-model="alert">
                <q-card>
                  <q-card-section>
                    <div class="text-h6">举报成功</div>
                  </q-card-section>
                  <q-card-actions align="right">
                    <q-btn flat label="OK" color="primary" v-close-popup />
                  </q-card-actions>
                </q-card>
              </q-dialog>
            </q-card-actions>
          </q-card>
        </template>
      </div>
      <template v-slot:loading>
        <div class="row justify-center q-my-md">
          <q-spinner-dots color="primary" size="40px" />
        </div>
      </template>
      <q-page-sticky position="bottom-right" :offset="[20, 18]">
        <q-btn fab icon="add" @click="add" color="light-blue-3" />
        <q-btn fab icon="mail" @click="drift" color="blue-3" />
      </q-page-sticky>
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
      offset: 1,
      errmsg: '',
      prompt: false,
      alert: false,
      reports: ''
    }
  },

  methods: {
    updateEkil (e) {
      this.getEkil(e)
      this.$axios.get('reviews/countekil', {
        params: {
          rid: e.rid
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        e.ekil = response.data
      })
        .catch((error) => {
          console.log(error)
        })
    },
    getEkil (e) {
      this.$axios.get('reviews/getekil', {
        params: {
          rid: e.rid
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        e.liked = response.data
      })
        .catch((error) => {
          console.log(error)
        })
    },
    onLoad (index, done) {
      this.$axios.get('reviews/bylimit', {
        params: {
          offset: this.$data.offset,
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
          data.forEach(e => {
            e.ekil = 0 // required
            this.updateEkil(e)
          })
          data.forEach(e => this.items.push(e))
          this.offset = data[data.length - 1].rid + 1
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
    reportto (rid) {
      this.$axios.post('comments/new', {
        reviewId: rid,
        content: this.$data.reports
      },
      {
        headers: {
          token: this.$gStore.token
        }
      })
        .then((response) => {
          console.log(response.data)
          if (!response.data.succ) {
            console.log('举报失败')
            this.$data.errmsg = response.data.msg
          } else {
            console.log('举报成功')
            this.$data.alert = true
          }
        })
        .catch(() => {
          alert('error')
        })
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
        if (response.data[0]) {
          console.log('调用了ekil' + response.data[0])
          return response.data[0]
        }
      })
        .catch((error) => {
          console.log(error)
        })
    },
    setekil (rid, ekil, obj) {
      this.$axios.get('reviews/setekil', {
        params: {
          rid: rid,
          ekil: ekil
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (!response.data.succ) {
          this.$data.errmsg = response.data.msg
        } else {
          console.log('点赞成功')
          this.updateEkil(obj)
        }
      })
        .catch((error) => {
          console.log(error)
        })
    },
    add () {
      this.$router.push('/newreview')
    },
    drift () {
      this.$router.push('/drifting')
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
