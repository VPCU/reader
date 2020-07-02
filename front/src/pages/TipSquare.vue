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
            <q-card-section horizontal>

              <q-card-section class="q-pt-xs">
                <div class="text-overline">Overline</div>
                <div class="text-h5 q-mt-sm q-mb-xs">标题</div>
                <div class="text-caption text-grey">
                  {{item}}
                </div>
              </q-card-section>
            </q-card-section>

            <q-card-section>
            </q-card-section>

            <q-separator />

            <q-card-actions align="right">
              <q-btn flat round color="red" icon="favorite" />
              <q-btn flat round color="teal" icon="bookmark" />
              <q-btn flat round color="primary" icon="share" />
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
export default {
  data () {
    return {
      items: ['Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 2, 3, 4, 5, 6]
    }
  },

  methods: {
    onLoad (index, done) {
      setTimeout(() => {
        if (this.items) {
          this.items.splice(0, 0, {}, {}, {}, {}, {}, {}, {})
          done()
        }
      }, 2000)
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
