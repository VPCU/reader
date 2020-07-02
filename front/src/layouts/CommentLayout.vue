<template>
  <q-layout view="hHh LpR fFf" class="bg-grey-3">
    <q-footer elevated>
      <q-toolbar class="GPLAY__toolbar text-grey-6">
        <div class="cursor-pointer">
          <q-popup-edit v-model="nickname" :validate="val => val.length > 5">
            <template v-slot="{ initialValue, value, emitValue, validate, set, cancel }">
              <q-input
                type="textarea"
                autofocus
                dense
                counter
                :value="nickname"
                hint="Your nickname"
                :rules="[
              val => validate(value) || 'More than 5 chars required'
            ]"
                @input="emitValue"
              >
                <template v-slot:after>
                  <q-btn flat dense color="negative" icon="cancel" @click.stop="cancel" />
                  <q-btn flat dense color="positive" icon="check_circle" @click.stop="set" :disable="validate(value) === false || initialValue === value" />
                </template>
              </q-input>
            </template>
          </q-popup-edit>
        </div>
      </q-toolbar>
    </q-footer>
    <!--    <q-drawer-->
    <!--      v-model="leftDrawerOpen"-->
    <!--      show-if-above-->
    <!--      bordered-->
    <!--      content-class="bg-grey-3 text-grey-7"-->
    <!--      :width="200"-->
    <!--    >-->
    <!--      <q-list>-->

    <!--        <q-item clickable class="GPLAY__drawer-link bg-grey-10 text-grey-3">-->
    <!--          <q-item-section avatar class="text-grey-1">-->
    <!--            <q-icon name="weekend" />-->
    <!--          </q-item-section>-->
    <!--          <q-item-section class="text-grey-3">-->
    <!--            <q-item-label>Entertainment</q-item-label>-->
    <!--          </q-item-section>-->
    <!--        </q-item>-->

    <!--        <q-item clickable class="GPLAY__drawer-link GPLAY__drawer-link&#45;&#45;apps">-->
    <!--          <q-item-section avatar class="bg-green-7 text-grey-1 text-center">-->
    <!--            <q-icon name="android" />-->
    <!--          </q-item-section>-->
    <!--          <q-item-section class="apps-text">-->
    <!--            <q-item-label>Apps</q-item-label>-->
    <!--          </q-item-section>-->
    <!--        </q-item>-->

    <!--        <q-item clickable class="GPLAY__drawer-link GPLAY__drawer-link&#45;&#45;movies">-->
    <!--          <q-item-section avatar class="movies-icon bg-red-7 text-grey-1 text-center">-->
    <!--            <q-icon name="local_movies" />-->
    <!--          </q-item-section>-->
    <!--          <q-item-section class="movies-text">-->
    <!--            <q-item-label>Movies & TV</q-item-label>-->
    <!--          </q-item-section>-->
    <!--        </q-item>-->

    <!--        <q-item clickable class="GPLAY__drawer-link GPLAY__drawer-link&#45;&#45;music">-->
    <!--          <q-item-section avatar class="music-icon bg-orange-7 text-grey-1 text-center">-->
    <!--            <q-avatar size="22px" color="white" text-color="orange-7" icon="music_note" />-->
    <!--          </q-item-section>-->
    <!--          <q-item-section class="music-text">-->
    <!--            <q-item-label>Music</q-item-label>-->
    <!--          </q-item-section>-->
    <!--        </q-item>-->

    <!--        <q-item clickable class="GPLAY__drawer-link GPLAY__drawer-link&#45;&#45;books">-->
    <!--          <q-item-section avatar class="books-icon bg-blue-7 text-grey-1 text-center">-->
    <!--            <q-icon name="book" />-->
    <!--          </q-item-section>-->
    <!--          <q-item-section class="books-text">-->
    <!--            <q-item-label>Books</q-item-label>-->
    <!--          </q-item-section>-->
    <!--        </q-item>-->

    <!--        <q-item clickable class="GPLAY__drawer-link GPLAY__drawer-link&#45;&#45;devices">-->
    <!--          <q-item-section avatar class="devices-icon bg-blue-grey-7 text-grey-1 text-center">-->
    <!--            <q-icon name="devices" />-->
    <!--          </q-item-section>-->
    <!--          <q-item-section class="devices-text">-->
    <!--            <q-item-label>Devices</q-item-label>-->
    <!--          </q-item-section>-->
    <!--        </q-item>-->

    <!--        <q-separator class="q-mb-md" />-->

    <!--        <q-item v-for="link in links1" :key="link.text" v-ripple dense clickable class="GPLAY__drawer-item">-->
    <!--          <q-item-section class="text-grey-8">-->
    <!--            <q-item-label>{{ link.text }}</q-item-label>-->
    <!--          </q-item-section>-->
    <!--        </q-item>-->
    <!--      </q-list>-->
    <!--    </q-drawer>-->

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
      leftDrawerOpen: false,
      search: '',
      storage: 0.26,
      nickname: 'Click me',
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
    add () {
      this.$router.push('/')
    },
    my () {
      this.$router.push('/my')
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
