<template>
  <q-page padding class="docs-input row justify-center">
    <div class="q-ma-sm" style="width: 1000px; max-width: 100vw;">
      <h4 class="q-mt-sm q-mb-sm">编辑漂流</h4>
      <div class="q-caption" color="grey">漂流编号:{{ driftingid }}</div>
      <q-input :value="bookName" readonly="true" stack-lable="书籍名称" />
      <q-input :value="bookAuthor" readonly="true" stack-lable="书籍作者" />
      <q-input :value="isbn" @change="val => isbn = val" stack-lable="ISBN" />
      <q-input :value="location" @change="val => location = val" stack-label="寄存处" />
      <div class="q-caption" color="grey">建议改为有人看管的地方，例如传达室、保安亭等。</div>
      <q-input :value="keeper" @change="val => keeper = val" stack-lable="保管人" />
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
      driftingid: '',
      bookName: '',
      bookAuthor: '',
      isbn: '',
      location: '',
      keeper: ''
    }
  },
  methods: {
    loadData () {
      this.$axios.get('driftingid', 'bookName', 'bookAuthor', 'isbn', 'location', 'keeper').then((response) => {
        console.log(response)
        this.driftingid = response.data.driftingid
        this.bookName = response.data.bookName
        this.bookAuthor = response.data.bookAuthor
        this.isbn = response.data.isbn
        this.location = response.data.location
        this.keeper = response.data.keeper
      })
    },
    commit () {
      this.$axios.post('newdrifting', {
        title: this.$data.title,
        bookName: this.$data.bookName,
        bookAuthor: this.$data.bookAuthor,
        isbn: this.$data.isbn,
        location: this.$data.location,
        keeper: this.$data.keeper
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
            console.log('发起成功')
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
