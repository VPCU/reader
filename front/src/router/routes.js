
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/TipSquare.vue') },
      { path: 'login', component: () => import('pages/Login.vue') },
      { path: 'signup', component: () => import('pages/SignUp.vue') },
      { path: 'newreview', component: () => import('pages/NewReview') },
      { path: 'drifting', component: () => import('pages/Drifting.vue') },
      { path: 'my', component: () => import('pages/My.vue') },
      { path: 'managedrifting', component: () => import('pages/ManageDrifting.vue') },
      { path: 'managereport', component: () => import('pages/ManageReport.vue') },
      { path: 'manageusers', component: () => import('pages/ManageUsers.vue') }
    ]
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
