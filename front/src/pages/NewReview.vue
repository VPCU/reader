<template>
  <q-page padding class="docs-input row justify-center">
    <div style="width: 1000px; max-width: 100vw;">

      <h4 class="q-mt-lg">新建书评</h4>

      <q-input v-model="text" placeholder="书评标题" />
      <q-input v-model="text" placeholder="书名" />
      <q-input v-model="text" placeholder="书籍作者" />
      <br>

      <q-editor v-model="model" />

      <q-rating color="amber" v-model="ratingModel" :max="5"  size="2rem" class="q-my-md" />
      <br>

      <q-btn icon="create" label="发布书评" />
    </div>
  </q-page>
</template>

<script>
export default {

}
</script>

<style scoped>

</style>
