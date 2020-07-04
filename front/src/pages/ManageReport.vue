<template>
  <q-list>
    <q-card inline class="bigger q-ma-sm q-pt-sm" v-for="{ reportId, content, reporter, review, detail } in report" :key="reportId">
      <div class="bigger q-ma-md">
        <q-card-title >
          <!-- probably use later
          <q-btn fab color="primary" icon="place" align="right" style="top: 0; right: 8px; transform: translateY(-50%);" />
          -->
          <h6 class="q-my-sm">{{ content }}</h6>
          <div slot="right" class="row items-center">
            由<q-icon name="face" /> {{ reporter.nick }}举报
          </div>
        </q-card-title>
        <br/>
        <q-card-main>
          <p>{{ review.content }}</p>
          <p class="text-faded">{{ detail }}</p>
        </q-card-main>
        <q-card-separator />
        <q-card-actions class="q-px-none q-px-none" align="center">
          <q-btn flat color="grey" icon="clear" @click="ignore(reportId)">忽略</q-btn>
          <q-btn flat color="red" icon="delete" @click="deletereview(reportId, review)">删除</q-btn>
          <q-btn flat color="orange" icon="report_problem" @click="deleteban(reportId, review)">删除并封禁</q-btn>
        </q-card-actions>
      </div>
    </q-card>
  </q-list>
</template>

<script>
export default {
  data () {
    return {
      report: []
      // [{ reportId: '1', reporttype: '违法行为', reportuser: '用户XXX', viewcontent: '被举报的违法书评内容被举报的违法书评内容', detail: '举报的细节信息' }, { reportid: '1', reporttype: '广告或垃圾信息', reportuser: '用户CCC', viewcontent: '被举报的广告书评内容被举报的广告书评内容', detail: '举报的细节信息' }]
    }
  },
  mounted: function () {
    this.createcode()// 加载页面时触发的函数
  },
  methods: {
    createcode () {
      this.$axios.get('/report/admin/reviews', {
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        if (response.data) {
          console.log(response.data)
          this.report = response.data
        }
      })
    },
    ignore (reportId) {
      this.$axios.post('/report/admin/reviews', {
        reportId: reportId,
        status: 'ignored',
        log: 'ignored'
      }, {
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data.code === 4401) this.$router.push('/login')
        console.log(response.data)
        if (!response.data.succ) {
          this.$data.errmsg = response.data.msg
          this.$data.alert = true
        } else {
          console.log('忽略成功')
          this.$gStore.token = response.data.token
          this.$router.push('/managedrifting')
        }
      }).catch(() => {
        alert('error')
      })
    },
    deletereview (reportId, review) {
      this.$axios.get('/admin/disablereview', {
        params: {
          rid: review.rid,
          disable: true
        },
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data) {
          console.log(response.data)
          this.$router.push('/managereport')
        }
      }).catch(() => {
        alert('error')
      })
      this.$methods.ignore(reportId)
    },
    deleteban (reportId, review) {
      this.$axios.get('/reviews/id/' + review.rid, {
        headers: {
          token: this.$gStore.token
        }
      }).then((response) => {
        if (response.data) {
          console.log(response.data)
          var uid = response.data.userId
          this.$axios.get('/admin/disableuser', {
            params: {
              id: uid
            },
            headers: {
              token: this.$gStore.token
            }
          })
        }
      }).catch(() => {
        alert('error')
      })
      this.$methods.deletereview(review)
      this.$methods.ignore(reportId)
    }
  }
}
</script>
