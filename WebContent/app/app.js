// import Vue from "./vue.js";
// import App from "./App.vue";
// import router from "../js/vue-router.js";
// import store from "./store";
// import "bootstrap";
// import "bootstrap/dist/css/bootstrap.min.css";
// import VueMeta from "vue-meta";

// Vue.config.productionTip = false;

// import { BootstrapVue } from "bootstrap-vue";
// Vue.use(BootstrapVue);

// Vue.use(VueMeta, {
//   refreshOnceOnNavigation: true,
// });

const HomeComponent = { template: '<home></home>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		 {
    path: '/',
    name: 'Home',
    component: HomeComponent
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
		
		  
	  ]
});

const app = new Vue({
  router,
  el: '#app',
});
