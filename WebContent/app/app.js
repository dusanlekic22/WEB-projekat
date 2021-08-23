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

const store = new Vuex.Store({
  state: {
      registrationActive: false,
  },
  mutations: {
    openRegistration(state) {
      state.registrationActive = true;
    },
    closeRegistration(state) {
       state.registrationActive = false;
    }
  },
  actions: {
    openRegistration(context) {
      context.openRegistration();
    },
     closeRegistration(context) {
      context. closeRegistration();
    }
  },
  getters: {
  isActive(state) {
      return state.registrationActive;
    }
  },
  modules: {},
})

const app = new Vue({
  store,
  router,
  el: '#app',
  data()
  {
    return{
      registration: false
    };
  },
  methods: {
    openSignIn() {
      this.registration = true;
    }
  },
  provide: {
    registr: this.registration
  }
});


Vue.use(store);
