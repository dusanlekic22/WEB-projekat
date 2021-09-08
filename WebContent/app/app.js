const HomeComponent = { template: '<home></home>' }
const ProfileComponent = { template: '<profile></profile>' }
const AdministratorCrudComponent = { template: '<administrator-crud></administrator-crud>' }
const CreateRestaurantComponent = { template: '<create-restaurant></create-restaurant>' }
const RestaurantPageComponent = { template: '<restaurant-page></restaurant-page>' }
const CreateArticleComponent = { template: '<create-article></create-article>' }
const ShowUsersComponent = { template: '<show-users></show-users>' }

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
      },
      {
        path: '/Restaurant/:id',
        name: 'Restaurant',
        component: RestaurantPageComponent,
      },
       {
        path: '/AdministratorCrud',
        name: 'AdministratorCrud',
        component: AdministratorCrudComponent
      },
        {
        path: '/CreateRestaurant',
        name: 'CreateRestaurant',
          component: CreateRestaurantComponent,
          children: [
          { name:'create-manager-for-restaurant', path: ':manager', component: AdministratorCrudComponent , props: true}
        ]
      },
        {
        path: '/CreateArticle',
        name: 'CreateArticle',
        component: CreateArticleComponent
      },
         {
        path: '/ShowUsers',
        name: 'ShowUsers',
        component: ShowUsersComponent
      }
	  ]
});

const store = new Vuex.Store({
  modules: {
    registrationModule: registrationStore,
    loginModule: loginStore,
    userModule: userStore,
    managerModule: managerStore,
    restaurantModule: restaurantStore,
    restaurantsModule: restaurantsStore,
    cartModule: cartStore
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
