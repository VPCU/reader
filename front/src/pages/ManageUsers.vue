<template>
  <div class="q-pa-md">
    <q-table
      title="用户信息"
      :data="data"
      :columns="columns"
      row-key="username"
      :selected-rows-label="getSelectedString"
      selection="multiple"
      :selected.sync="selected"
    />

    <!--输出选中信息
    <div class="q-mt-md">
      Selected: {{ JSON.stringify(selected) }}
    </div>
    -->

    <br>
    <div class="row justify-center">
    <q-btn push color="white" text-color="primary" label="封号" />
    &nbsp;&nbsp;&nbsp;
    <q-btn push color="white" text-color="primary" label="解封" />
    &nbsp;&nbsp;&nbsp;
    <q-btn push color="white" text-color="primary" label="删除"  icon="priority_high"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      selected: [],
      columns: [
        {
          name: 'username',
          required: true,
          label: '用户名',
          align: 'left',
          field: 'username',
          format: val => `${val}`,
          sortable: true
        },
        { name: 'password', align: 'left', label: '密码', field: 'password', sortable: true },
        { name: 'nick', align: 'left', label: '昵称', field: 'nick', sortable: true },
        { name: 'email', align: 'center', label: '邮箱', field: 'email' },
        { name: 'phone', align: 'center', label: '手机号', field: 'phone' }
      ],
      data: [
        {
          username: 'user0',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user1',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user2',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user3',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user4',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user5',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user6',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user7',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user8',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        },
        {
          username: 'user9',
          password: '15999',
          nick: 'superman',
          email: '123@qq.com',
          phone: '123456'
        }
      ]
    }
  },
  methods: {
    getSelectedString () {
      return this.selected.length === 0 ? '' : `${this.selected.length} record${this.selected.length > 1 ? 's' : ''} selected of ${this.data.length}`
    }
  }
}
</script>
