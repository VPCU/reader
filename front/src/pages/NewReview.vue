<template>
  <q-page padding class="docs-input row justify-center">
    <div style="width: 1000px; max-width: 100vw;">

      <h4 class="q-mt-lg q-mb-md">新建书评</h4>

      <q-input v-model="title" placeholder="书评标题" />
      <q-input v-model="bookName" placeholder="书名" />
      <q-input v-model="bookAuthor" placeholder="书籍作者" />
      <br>

      <q-editor v-model="content" />

      <q-rating color="amber" v-model="ratingModel" :max="5"  size="2rem" class="q-my-md" />
      <br>

      <q-btn icon="create" label="发布书评" @click="commit" />
    </div>

    <q-dialog v-model="alert">x
      <q-card>
        <q-card-section>
          <div class="text-h6">发布失败</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{errmsg}}
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="OK" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>

  </q-page>
</template>

<script>
import 'boot/axios'
import 'boot/store'
export default {
  name: 'NewReview',
  data () {
    return {
      alert: false,
      errmsg: '',
      title: '',
      bookName: '',
      bookAuthor: '',
      content: ''
    }
  },
  methods: {
    commit () {
      this.$axios.post('newreview', {
        title: this.$data.title,
        bookName: this.$data.bookName,
        bookAuthor: this.$data.bookAuthor,
        content: this.$data.content
      }, {
        headers: {
          token: this.$gStore.token
        }
      })
        .then((response) => {
          if (response.data.code === 4401) this.$router.push('/login')
          console.log(response.data)
          if (!response.data.succ) {
            this.$data.errmsg = response.data.msg
            this.$data.alert = true
          } else {
            console.log('发布成功')
          }
        })
        .catch(() => {
          alert('error')
        })
    }
  }
}
</script>
