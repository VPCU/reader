<template>
  <div class="q-pa-md">
    <q-card class="my-card">
      <q-img
        src="https://cdn.quasar.dev/img/parallax2.jpg"
        basic
      >
        <div class="absolute-bottom text-h6">
          Title
        </div>
      </q-img>

      <q-card-section>
        {{ lorem }}
      </q-card-section>
    </q-card>
    <div>
      {{p}}
    </div>
    <div class="q-pa-md">
      <q-infinite-scroll @load="onLoad" :offset="0">
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
              </q-card-actions>
            </q-card>

          </template>        </div>
        <template v-slot:loading>
          <div class="row justify-center q-my-md">
            <q-spinner-dots color="primary" size="40px" />
          </div>
        </template>
      </q-infinite-scroll>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReadReview',
  data () {
    return {
      lorem: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      items: ['ok', 'good', 'nice', 'come on'],
      p: this.$route.query.id
    }
  },
  methods: {
    onLoad (index, done) {
      setTimeout(() => {
        if (this.items) {
          this.items.push({}, {}, {}, {}, {}, {}, {})
          done()
        }
      }, 2000)
    }
  }
}

</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 100%
</style>
