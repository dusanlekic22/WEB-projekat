<template>
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
              <router-link class="nav-link" to="/Registrovanje"
                >Registrovanje</router-link
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  data() {
    return {
      productPage: false,
    };
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Open+Sans:400,700,800");
@import url("https://fonts.googleapis.com/css?family=Lobster");
input {
  width: 200px;
}
.header-area {
  position: relative;
  height: 68px;
  background: white;
  background-attachment: fixed;
  background-position: center center;
  background-repeat: no-repear;
  background-size: cover;
}
.navbar {
  position: absolute;
  left: 0;
  top: 0;
  padding: 0;
  width: 100%;
  transition: background 0.6s ease-in;
  z-index: 99999;
  height: 68px;
}
.navbar .navbar-brand {
  font-family: "Lobster", cursive;
  font-size: 2rem;
  color: black !important;
}

.navbar .navbar-toggler {
  position: relative;
  height: 50px;
  width: 50px;
  border: none;
  cursor: pointer;
  outline: none;
  background: black;
}
.navbar-dark .navbar-nav .nav-link {
  position: relative;
  color: black;
  font-size: 1.1rem;
}
.navbar-dark .navbar-nav .nav-link:focus,
.navbar-dark .navbar-nav .nav-link:hover {
  color: black;
}
.navbar.fixed-top {
  position: fixed;
  -webkit-animation: navbar-animation 0.6s;
  animation: navbar-animation 0.6s;
  background-color: rgba(0, 0, 0, 0.9);
}

@media screen and (max-width: 768px) {
  .navbar-brand {
    margin-left: 20px;
  }
  .navbar-nav {
    padding: 0 20px;
    background-color: rgba(0, 0, 0, 0.9);
  }
  .navbar.fixed-top .navbar-nav {
    background: transparent;
  }
  input {
    width: 160px;
  }
}
@media screen and (min-width: 767px) {
  .navbar-dark .navbar-nav .nav-link {
    padding: 23px 15px;
  }
  .navbar-dark .navbar-nav .nav-link::after {
    content: "";
    position: absolute;
    bottom: 15px;
    left: 15%;
    right: 15%;
    height: 1px;
    background-color: black;
    -webkit-transform: scaleX(0);
    -ms-transform: scaleX(0);
    transform: scaleX(0);
    transition: transform 0.4s ease-in;
  }
  .navbar-dark .navbar-nav .nav-link:hover::after {
    -webkit-transform: scaleX(1);
    -ms-transform: scaleX(1);
    transform: scaleX(1);
  }
}
@media screen and (max-width: 370px) {
  input {
    width: 120px;
  }
}
@media screen and (max-width: 280px) {
  input {
    width: 85px;
  }
}
</style>
