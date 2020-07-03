<template>
  <q-page padding class="docs-input row justify-center">
    <div class="q-ma-sm" style="width: 1000px; max-width: 100vw;">
      <h4 class="q-mt-sm q-mb-sm">编辑漂流</h4>
      <div class="q-caption" color="grey">漂流编号:{{ driId }}</div>
      <q-input :value="bookName" readonly stack-lable="书籍名称" />
      <q-input :value="author" readonly stack-lable="书籍作者" />
      <q-input :value="isbn" @change="val => isbn = val" stack-lable="ISBN" />
      <q-input :value="location" @change="val => location = val" stack-label="寄存处" />
      <div class="q-caption" color="grey">建议改为有人看管的地方，例如传达室、保安亭等。</div>
      <q-input :value="guard" @change="val => guard = val" stack-lable="保管人" />
      <br>
      <q-btn color="primary" class="full-width" icon="update" label="更新漂流" />
    </div>
  </q-page>
</template>

<script>
import 'boot/axios'
export default {
  data () {
    return {
      driId: this.$route.query.driId,
      bookName: '',
      author: '',
      isbn: '',
      location: '',
      keeper: ''
    }
  },
  mounted: function () {
    this.createcode()// 加载页面时触发的函数
  },
  methods: {
    createcode () {
      this.$axios.get('/drifting/id/' + 'driId', {
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data[0]) {
          console.log(response.data)
          this.$data.bookName = response.data.bookName
          this.author = response.data.author
          this.isbn = response.data.isbn
          this.location = response.data.curPosition
          this.keguardeper = response.data.guard
        }
      })
    },
    commit () {
      this.$axios.post('/drifting/edit', {
        bookName: this.$data.bookName,
        author: this.$data.bookAuthor,
        isbn: this.$data.isbn,
        curPosition: this.$data.location,
        guardian: this.$data.keeper
      }, {
        headers: {
          token: this.$gStore.token
        }
      })
        .then((response) => {
          console.log(response.data)
          if (!response.data.succ) {
            this.$data.errmsg = response.data.msg
            this.$data.alert = true
          } else {
            console.log('修改成功')
            this.$gStore.token = response.data.token
            this.$router.push('/managedrifting')
          }
        })
        .catch(() => {
          alert('error')
        })
    }
  },
  created () {
    this.loadData()
  }
}
</script>

<style scoped>
</style>
