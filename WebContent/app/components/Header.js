Vue.component("the-header", {

  template: `
  <header class="header-area overlay">
    <nav class="shadow-sm navbar navbar-expand-md navbar-dark">
      <div class="container">
        <div class="col-lg-1">
          <a href="/" class="navbar-brand">MFood</a>
        </div>
        <div class="col-lg-4"></div>
        <div class="col-lg-1" v-if="productPage"><input type="text" /></div>
        <div class="col-lg-1">
          <button
            class="navbar-toggler"
            data-toggle="collapse"
            data-target="#main-nav"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
        <div id="main-nav" class="collapse navbar-collapse">
          <ul class="navbar-nav ms-auto">
            <li>
              <router-link class="nav-link" to="/Prijava">Prijava</router-link>
            </li>
            <li>
              <router-link
                class="nav-link"
                to="/"
                @click.native="openRegistration"
                >Registrovanje</router-link
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
  },
    mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/header.css';
        document.head.appendChild(style);
    }
  ,




});