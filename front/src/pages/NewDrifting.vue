<template>
  <q-page padding class="docs-input row justify-center">
    <div class="q-ma-sm" style="width: 1000px; max-width: 100vw;">

        <h4 class="q-mt-sm q-mb-md">发起漂流</h4>

        <q-input v-model="bookName" float-lable="书籍名称" placeholder="书籍名称"/>
        <q-input v-model="bookAuthor" placeholder="书籍作者" />
        <q-input v-model="isbn" placeholder="ISBN" />
        <q-input v-model="location" placeholder="寄存处" />
        <div class="q-caption" color="grey">建议选择有人看管的地方，例如传达室、保安亭等。</div>
        <!--<q-checkbox class="q-my-sm" v-model="iskeeper" lable="我选择的地点有人看管。"/>-->
        <q-input v-model="keeper" placeholder="保管人" />
        <br>
        <div class="q-caption" color="grey">请上传书籍现况照片</div>
        <q-uploader class="full-width" color="primary" inverted
                    float-label="请上传书籍现况照片" extensions=".jpg" fieldName="file"
                    :url="this.$axios.defaults.baseURL+'/upload'"
                    @uploaded="fileUploaded"/>

        <q-checkbox v-model="checked" color="secondary" label="我已知晓并愿意承担发起漂流可能面临的风险。" @input="check" />
        <br>

        <q-btn id="start1" :color="color1" class="full-width" icon="create" label="发起漂流" :disabled="disable1" @click="commit" />
    </div>
  </q-page>
</template>

<script>
import 'boot/axios'
import 'boot/store'
export default {
  name: 'Drifting',
  data () {
    return {
      // iskeeper: false,
      checked: false,
      color1: 'faded',
      disable1: true,

      bookName: '',
      bookAuthor: '',
      isbn: '',
      location: '',
      keeper: ''
    }
  },
  methods: {
    fileUploaded ({ files, xhr }) {
      console.log('上传成功')
      console.log(JSON.parse(xhr.response))
    },
    check () {
      if (this.$data.checked === true) {
        this.$data.color1 = 'primary'
        this.$data.disable1 = !this.$data.checked
      } else {
        this.$data.color1 = 'faded'
        this.$data.disable1 = !this.$data.checked
      }
    },
    commit () {
      this.$axios.post('/drifting/new', {
        bookName: this.$data.bookName,
        author: this.$data.bookAuthor,
        isbn: this.$data.isbn,
        position: this.$data.location,
        guardian: this.$data.keeper
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
            console.log('发起成功')
            this.$gStore.token = response.data.token
            this.$router.push('/managedrifting')
          }
        })
        .catch(() => {
          alert('error')
        })
    }
  }
}
</script>

<style scoped>
</style>
