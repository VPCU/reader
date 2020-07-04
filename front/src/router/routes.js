
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/TipSquare.vue') },
      { path: 'newreview', component: () => import('pages/NewReview') },
      { path: 'newdrifting', component: () => import('pages/NewDrifting.vue') },
      { path: 'drifting', component: () => import('pages/Drifting.vue') },
      { path: 'my', component: () => import('pages/My.vue') },
      { path: 'mysettings', component: () => import('pages/MySettings.vue') },
      { path: 'mymessages', component: () => import('pages/MyMessages.vue') },
      { path: 'messagedetail', component: () => import('pages/MessageDetail.vue') },
      { path: 'myfriends', component: () => import('pages/MyFriends.vue') },
      { path: 'friendinfo', component: () => import('pages/FriendInfo.vue') },
      { path: 'managedrifting', component: () => import('pages/ManageDrifting.vue') },
      { path: 'editdrifting', component: () => import('pages/EditDrifting.vue') },
      { path: 'managereport', component: () => import('pages/ManageReport.vue') },
      { path: 'manageusers', component: () => import('pages/ManageUsers.vue') },
      { path: 'readreview', component: () => import('pages/ReadReview.vue') },
      { path: 'readmyreview', component: () => import('pages/Myreview.vue') }
    ]
  },
  { path: '/login', component: () => import('pages/Login.vue') },
  { path: '/signup', component: () => import('pages/SignUp.vue') },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Login.vue')
  }
]

export default routes
