<template>
  <q-list>
    <q-card inline class="bigger q-ma-sm" v-for="{ reportid, reporttype, reportuser, viewcontent, detail } in report" :key="reportid">
      <div class="bigger q-ma-lg">
        <q-card-title >
          <!-- probably use later
          <q-btn fab color="primary" icon="place" align="right" style="top: 0; right: 8px; transform: translateY(-50%);" />
          -->
          <h6 class="q-mt-md q-mb-sm">{{ reporttype }}</h6>
          <div slot="right" class="row items-center">
            由<q-icon name="face" /> {{ reportuser }}举报
          </div>
        </q-card-title>
        <br/>
        <q-card-main>
          <p>{{ viewcontent }}</p>
          <p class="text-faded">{{ detail }}</p>
        </q-card-main>
        <q-card-separator />
        <q-card-actions class="q-px-none q-px-none" align="center">
          <q-btn flat color="grey" icon="clear" >忽略</q-btn>
          <q-btn-dropdown flat color="red" icon="rule" label="处理">
            <q-list link>
              <q-item>
                <q-item-main>
                  <q-item-tile label>删除该书评</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item>
                <q-item-main>
                  <q-item-tile label>删除该书评并封禁发布用户</q-item-tile>
                </q-item-main>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-card-actions>
      </div>
    </q-card>
  </q-list>
</template>

<script>
export default {
  data () {
    return {
      report: [{ reportid: '1', reporttype: '违法行为', reportuser: '用户XXX', viewcontent: '被举报的违法书评内容被举报的违法书评内容', detail: '举报的细节信息' }, { reportid: '1', reporttype: '广告或垃圾信息', reportuser: '用户CCC', viewcontent: '被举报的广告书评内容被举报的广告书评内容', detail: '举报的细节信息' }]
    }
  }
}
</script>
