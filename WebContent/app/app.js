const HomeComponent = { template: '<home></home>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		 {
    path: '/',
    name: 'Home',
    component: HomeComponent
  }
	  ]
});

const store = new Vuex.Store({
  modules: {
    registrationModule:registrationStore
  },
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
})

const app = new Vue({
  store,
  router,
  el: '#app',
});


Vue.use(store);
