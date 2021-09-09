Vue.component("the-header", {

  template: `
  <header class="header-area overlay">
  <nav class="navbar sticky-top navbar-expand-lg navbar-dark">
    <div class="container">
      <router-link to="/" class="navbar-brand">MFood</router-link>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
  </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto w-100 justify-content-end">
            <li v-if="!isLogged">
              <router-link
                class="nav-link"
                to="/"
                @click.native="openLogin"
                >Prijava</router-link>
            </li>
            <li v-if="!isLogged">
              <router-link 
                class="nav-link"
                to="/"
                @click.native="openRegistration"
                >Registrovanje</router-link
              >
            </li>
            <li v-if="isLogged">
              <router-link
                class="nav-link"
                to="/Profil"
                >Profil</router-link
              >
            </li>
            <li v-if="isAdmin">
              <router-link
                class="nav-link"
                to="/AdministratorCrud"
                >CRUD</router-link
              >
            </li>
               <li v-if="isAdmin">
              <router-link
                class="nav-link"
                to="/createRestaurant"
                >Add Restaurant</router-link
              >
            </li>
                 <li v-if="isAdmin">
              <router-link
                class="nav-link"
                to="/ShowUsers"
                >Korisnici</router-link
              >
            </li>
                 <li v-if="isManager">
              <router-link
                class="nav-link"
                to="/CreateArticle"
                >Dodaj Artikal</router-link
              >
            </li>
            </li>
                 <li v-if="canSeeOrders">
              <router-link
                class="nav-link"
                to="/Orders"
                >Moje Porudzbine</router-link
              >
            </li>
        </ul>
      </div>
    </div>
  </nav>


  </header>
`,

  data() {
    return {
      productPage: false,
    };
  },
  methods: {
    openRegistration() {
      this.$store.commit("openRegistration");
    },
     openLogin() {
      this.$store.commit('loginModule/openLogin');
    }
  },
    mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/header.css';
        document.head.appendChild(style);
        return this.$store.dispatch('userModule/setCurrentUser');
    }
  ,
  computed: {
    isLogged() {
      return this.$store.getters['userModule/logged'];
    },
    isAdmin() {
      return this.$store.getters['userModule/isAdmin'];
    },
     isManager() {
      return this.$store.getters['userModule/isManager'];
    },
    canSeeOrders() {
      return this.$store.getters['userModule/canSeeOrders'];
    },
    }




});
