// src/boot/axios.js

import Vue from 'vue'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'
Vue.prototype.$axios = axios
// ^ ^ ^ this will allow you to use this.$axios
//       so you won't necessarily have to import axios in each vue file
