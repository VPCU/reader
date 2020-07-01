<template>
  <q-page padding class="docs-input row justify-center">
    <div style="width: 1000px; max-width: 100vw;">

        <h4 class="q-mt-lg">发起漂流</h4>

        <q-input v-model="text" placeholder="书籍名称" />
        <q-input v-model="text" placeholder="书籍作者" />
        <q-input v-model="text" placeholder="ISBN" />
        <q-input v-model="text" placeholder="寄存处" />
        <div class="q-caption" color="grey">建议选择有人看管的地方，例如传达室、保安亭等。</div>
        <q-input v-model="text" placeholder="保管人" />
        <br>
        <div class="q-caption" color="grey">请上传书籍现况照片</div>
        <q-uploader color="primary" inverted float-label="请上传书籍现况照片" extensions=".jpg" :url="url" />

        <q-checkbox v-model="checked" color="secondary" label="我已知晓并愿意承担发起漂流可能面临的风险。" />
        <br>

        <q-btn color="primary" class="full-width" icon="create" label="发起漂流"/>
    </div>
  </q-page>
</template>

<script>

export default {
  data () {
    return {
      checked: false
    }
  }
}
</script>

<style scoped>
</style>
