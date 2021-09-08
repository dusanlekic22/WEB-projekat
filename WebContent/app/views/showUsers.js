Vue.component('show-users', {
  template:
  `
  <div class="container-fluid" v-if="updatedUsers.length !== 0">
	<div class="container pt-4">
		<h2>Korisnici sistema</h2> </div>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-sm-12 ">
				 <h2 class="pt-5">
               Administratori
         </h2>
         	<div class="d-flex pt-2">
        <div class="pt-2 m-auto"> Korisnicko ime </div>
        <div class="pt-2 m-auto"> Ime </div>
        <div class="pt-2 m-auto"> Prezime </div>
        </div>
         <base-user v-for="a in admins" :name="a.name" :surname="a.surname" :username="a.username"></base-user>
       </div>
	 </div>
   		<div class="row">
			<div class="col-lg-12 col-sm-12 ">
				 <h2 class="pt-5">
              Menadzeri
         </h2>
          <div class="pt-2 m-auto"> Korisnicko ime </div>
        <div class="pt-2 m-auto"> Ime </div>
        <div class="pt-2 m-auto"> Prezime </div>
          <base-user v-for="m in managers" :name="m.name" :surname="m.surname" :username="m.username"></base-user>
       </div>
	 </div>
   	<div class="row">
			<div class="col-lg-12 col-sm-12 ">
				 <h2 class="pt-5">
              Dostavljaci
         </h2>
        <div class="pt-2 m-auto"> Korisnicko ime </div>
        <div class="pt-2 m-auto"> Ime </div>
        <div class="pt-2 m-auto"> Prezime </div>
          <base-user v-for="d in delivers" :name="d.name" :surname="d.surname" :username="d.username"></base-user>
       </div>
	 </div>
   	<div class="row">
			<div class="col-lg-12 col-sm-12 ">
				 <h2 class="pt-5">
             Kupci
         </h2>
          <div class="pt-2 m-auto"> Korisnicko ime </div>
        <div class="pt-2 m-auto"> Ime </div>
        <div class="pt-2 m-auto"> Prezime </div>
        <base-user v-for="c in customers" :name="c.name" :surname="c.surname" :username="c.username"></base-user>
       </div>
	 </div>
	</div>
  </div>
</div>
  `,
  name: "showUsers",
  data() {
    return {
      users: [],
      managers: [],
      delivers: [],
      admins: [],
      customers: []
    };
  },
   mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        // style.href = 'css/showUsers.css';
     document.head.appendChild(style);
     this.$store.dispatch('userModule/getUsers');
  },
  computed: {
    updatedUsers() {
      this.users = this.$store.getters['userModule/getterUsers']
      this.admins = this.getByRole('ADMINISTRATOR');
      this.managaers = this.getByRole('MANAGER');
      this.delivers = this.getByRole('DELIVERY');
      this.customers = this.getByRole('CUSTOMER');
      return this.users;
    },
  },
  methods: {
    getByRole(role) {
      return this.users.filter((user) => {
        return user.role === role;
      })
    }
  },

});
