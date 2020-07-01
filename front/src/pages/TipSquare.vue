<template>
  <div class="q-pa-md">
    <q-infinite-scroll @load="onLoad" :offset="250">
      <div v-for="(item, index) in items" :key="index" class="caption">
        <template>
            <q-banner rounded class="bg-grey-3">
              <template v-slot:avatar>
                <img
                  src="https://cdn.quasar.dev/img/mountains.jpg"
                  style="width: 30px; height: 30px"
                >
              </template>
              {{item}}
              <q-btn flat label="Retry" />
              <q-tabs
                width = 100%
                v-model="tab"
                dense
                class="bg-teal text-yellow shadow-2"
              >
                <q-tab name="mails" icon="mail" />
                <q-tab name="alarms" icon="alarm" />
                <q-tab name="movies" icon="movie" />
              </q-tabs>
            </q-banner>
        </template>
      </div>
      <template v-slot:loading>
        <div class="row justify-center q-my-md">
          <q-spinner-dots color="primary" size="40px" />
        </div>
      </template>
    </q-infinite-scroll>
  </div>
</template>

<script>
export default {
  data () {
    return {
      items: ['0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 1, 2, 3, 4, 5, 6]
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
    }
  }
}
</script>
