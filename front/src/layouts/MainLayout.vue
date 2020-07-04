<template>
  <q-layout view="hHh LpR fFf" class="bg-grey-3">
    <q-header class="bg-grey-3 text-grey-9" reveal height-hint="60">
      <q-toolbar class="GPLAY__toolbar text-grey-6">
        <q-btn
          flat
          dense
          round
          @click="goBack"
          aria-label="Menu"
          icon="arrow_back"
          class="q-mr-sm"
        />
        <q-space />

        <div class="GPLAY__toolbar-input-container row no-wrap">
          <q-input dense outlined square v-model="search" placeholder="Search" class="bg-white col" />
          <q-btn class="GPLAY__toolbar-input-btn" width = "5px" color="primary" icon="search" unelevated />
        </div>

        <q-space />

        <div class="q-pl-md q-gutter-sm row no-wrap items-center">
          <q-btn round dense flat color="grey-8" icon="notifications" @click = "message">
            <q-badge color="red" text-color="white" floating>
              2
            </q-badge>
            <q-tooltip>Notifications</q-tooltip>
          </q-btn>

          <q-btn round flat @click = "my">
            <q-avatar size="26px">
              <img :src="this.iconSrc">
            </q-avatar>
            <q-tooltip>Account</q-tooltip>
          </q-btn>
        </div>
      </q-toolbar>
    </q-header>
    <q-footer elevated>
      <q-tabs v-model="tab">
        <q-tab name="Tips" @click="add"  label="广场" />
        <q-tab name="Group" @click="drifting" label="漂流" />
        <q-tab name="Mall" @click="mall" label="商城" />
        <q-tab name="MySelf" @click="my" label="我的" />
      </q-tabs>
    </q-footer>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'GooglePlayLayout',
  data () {
    return {
      search: '',
      storage: 0.26,
      iconSrc: this.$gStore.user.imgSrc,
      links1: [
        { text: 'Account' },
        { text: 'Payment methods' },
        { text: 'My subscriptions' },
        { text: 'Redeem' },
        { text: 'Buy gift card' },
        { text: 'My wishlist' },
        { text: 'My Play activity' },
        { text: 'Parent guide' }
      ]
    }
  },
  methods: {
    goBack () {
      this.$router.back()
    },
    tab () {
      // tab
    },
    add () {
      this.$router.push('/')
    },
    drifting () {
      this.$router.push('/drifting')
    },
    my () {
      this.$router.push('/my')
    },
    mall () {
      this.$router.push('/mall')
    },
    message () {
      this.$router.push('/mymessages')
    }
  }
}

</script>
<style lang="sass">
  .GPLAY
    &__toolbar
      height: 60px
    &__logo
      width: 183px
      height: 39px
    &__toolbar-input-container
      min-width: 100px
      width: 55%
    &__toolbar-input-btn
      border-radius: 0
      max-width: 30px
      width: 100%
    &__drawer-link
    .q-item__section--avatar
      margin: -8px 0 -8px -16px
      padding: 8px 0 8px 16px
      .q-item__section--main
        margin: -8px -16px -8px 16px
        padding: 8px 16px 8px 2px
        font-size: 18px
        font-weight: 300
      &--apps, &--movies, &--music, &--books, &--devices
        background: #f5f5f5!important
        &:hover
          color: #eee !important
      &--apps:hover
        background: #43a047!important
      &--movies:hover
        background: #e53935!important
      &--music:hover
        background: #fb8c00!important
      &--books:hover
        background: #1e88e5!important
      &--devices:hover
        background: #546e7a!important
    &__drawer-item
      padding: 6px 12px 6px 23px
    &__sticky
      min-height: 49px
      border-bottom: 1px solid rgba(0,0,0,0.12)
    &__sticky-help
      border: 1px solid #ccc
      padding-left: 8px
      padding-right: 8px
    &__sticky-settings
      padding-left: 17px
      padding-right: 17px
      border: 1px solid #ccc
</style>
