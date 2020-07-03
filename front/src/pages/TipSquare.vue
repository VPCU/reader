<template>
  <div class="q-pa-md">
    <q-infinite-scroll @load="onLoad" :offset="250">
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
            <q-card-section horizontal @click="readdetail">

              <q-card-section class="q-pt-xs">
                <div class="text-overline">Overline</div>
                <div class="text-h5 q-mt-sm q-mb-xs">{{String(item.title)}}</div>
                <div class="text-caption text-grey" >
                 <p v-html="$options.filters.ellipsis(String(item.content))"></p>
                </div>
              </q-card-section>
            </q-card-section>

            <q-card-section>
            </q-card-section>

            <q-separator />

            <q-card-actions align="right">
              <q-btn flat round color="red" icon="favorite" />
              <q-btn flat round color="teal" icon="bookmark" />
              <q-btn flat round color="primary" @click="prompt = true"  icon="share" />
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
                    <q-btn flat label="确认举报" @click="reportto(index)" v-close-popup />
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
      <q-page-sticky position="bottom-right" :offset="[18, 18]">
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
      if (value.length > 50) {
        return value.slice(0, 50) + '...'
      }
      return value
    }
  },
  data () {
    return {
      items: [],
      offset: 1,
      limit: 1,
      tipnum: 5,
      desc: true,
      errmsg: '',
      prompt: false,
      reports: ''
    }
  },

  methods: {
    onLoad (index, done) {
      this.$axios.get('reviews/bylimit', {
        params: {
          offset: this.$data.offset,
          limit: this.$data.limit,
          desc: this.$data.desc
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        // console.log('response+++++++++++++++++')
        // console.log(response.data[0].content)
        this.items.push(response.data[0])
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
    reportto (index) {
      console.log('举报的是哪一个+++' + index)
    },
    readdetail () {
      this.$router.push({
        path: '/readreview',
        query: { id: '1' }
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
