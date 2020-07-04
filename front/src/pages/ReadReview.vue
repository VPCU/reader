<template>
  <div class="q-pa-md">
    <q-card class="my-card bg-secondary text-white" @load ="onTips">
      <q-card-section>
        <div class="text-h6">{{this.content.title}}</div>
        <div class="text-subtitle2">by {{this.content.userNick}}</div>
      </q-card-section>

      <q-card-section>
        <p v-html="$options.filters.ellipsis(content.content)"></p>
      </q-card-section>

      <q-separator dark />

      <q-card-actions>
        <q-btn flat>点  赞</q-btn>
        <q-btn flat>收  藏</q-btn>
        <q-btn flat>举  报</q-btn>
      </q-card-actions>
    </q-card>
    <div>
      <q-chip size="18px" icon="bookmark">
        评论内容
      </q-chip>
    </div>
    <q-dialog v-model="comment" persistent>
      <q-card style="max-width: 300px">
        <q-card-section>
          <div class="text-h6">请输入评论内容</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input type="textarea" dense v-model="reports" autofocus @keyup.enter="comment = false" />
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="取消" v-close-popup />
          <q-btn flat label="评论" @click="reportto" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
    <q-dialog v-model="alert">
      <q-card>
        <q-card-section>
          <div class="text-h6">评论成功</div>
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat label="OK" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
    <div class="q-pa-md">
      <q-infinite-scroll @load="onLoad" ref="infiniteScroll" :offset="0">
        <div v-for="(item, index) in items" :key="index" class="caption">
          <template>
            <q-card flat bordered class="my-card bg-grey-1">
              <!--            这里控制用户头像和用户名-->
              <q-item>
                <q-item-section avatar>
                  <q-avatar>
                    <img :src="item.imgSrc">
                  </q-avatar>
                </q-item-section>
              </q-item>

              <q-separator />
              <!--            这里控制该书评的内容-->
              <q-card-section horizontal>

                <q-card-section class="q-pt-xs">
                  <!--<div class="text-overline">Overline</div>
                  <div class="text-h5 q-mt-sm q-mb-xs">标题</div>-->
                  <div class="text-caption text-grey">
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
              </q-card-actions>
            </q-card>

          </template>
        </div>
        <template v-slot:loading>
          <div class="row justify-center q-my-md">
            <q-spinner-dots color="primary" size="40px" />
          </div>
        </template>
      </q-infinite-scroll>
    </div>
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <q-btn fab icon="add" @click="comment = true" color="light-blue-3" />
    </q-page-sticky>
  </div>
</template>

<script>
export default {
  name: 'ReadReview',
  filters: {
    // 当渲染的文字超出30字后显示省略号
    ellipsis (value) {
      if (!value) return ''
      // if (value.length > 50) {
      //   return value.slice(0, 50) + '...'
      // }
      return value
    }
  },
  data () {
    return {
      content: null,
      items: [],
      rid: this.$route.query.rid,
      reports: '',
      comment: false
    }
  },
  mounted () {
    this.onTips()
  },
  methods: {
    onTips () {
      this.$axios.get('reviews/id/' + this.rid, {
        params: {
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(response.data)
        this.content = response.data
      })
        .catch((error) => {
          console.log(error)
        })
    },
    // 这是用于刷新评论的函数
    onLoad (index, done) {
      this.$axios.get('comments/all', {
        params: {
          rid: this.$data.rid
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(response.data)
        response.data.forEach(e => this.items.push(e))
        this.$refs.infiniteScroll.stop()
        done()
      })
        .catch((error) => {
          console.log(error)
        })
    },
    // 这是用于评论的函数
    reportto () {
      this.$axios.post('comments/new', {
        reviewId: this.rid,
        content: this.$data.reports
      },
      {
        headers: {
          token: this.$gStore.token
        }
      })
        .then((response) => {
          if (response.data.code === 4401) this.$router.push('/login')
          console.log(response.data)
          if (!response.data.succ) {
            console.log('评论失败')
            this.$data.errmsg = response.data.msg
          } else {
            console.log('评论成功')
            this.$data.alert = true
          }
        })
        .catch(() => {
          alert('error')
        })
    }
  }
}

</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 100%
</style>
