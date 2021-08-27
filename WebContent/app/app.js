const HomeComponent = { template: '<home></home>' }
const ProfileComponent = { template: '<profile></profile>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		 {
    path: '/',
    name: 'Home',
    component: HomeComponent
      },
      {
        path: '/Profil',
        name: 'Profil',
        component: ProfileComponent
      }
	  ]
});

const store = new Vuex.Store({
  modules: {
    registrationModule: registrationStore,
    loginModule: loginStore,
    userModule: userStore
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
