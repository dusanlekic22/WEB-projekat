Vue.component('home', {
  template:
  `
  <div class="container-fluid">
    <section>
      <div id="searchSection">
        <div class="container py-5">
          <div class="row py-4">
            <div class="col-lg-7 pt-5 text-center">
              <h1 class="pt-5">
                Želiš da isprobaš nešto novo?
              </h1>
              <places></places>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="mt-5 cards-section">
    <div class="container">
      <h2>Istraži gradove u kojima ćeš naći MFOOD</h2>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-sm-12 ">
            <h2 class="pt-5">
              🇷🇸 Srbija <br />
              <button class="showCountries">Prikaži sve zemlje</button>
            </h2>
          </div>
          <div class="col-lg-8 col-sm-2">
            <city
              v-for="city in cities"
              :key="city.id"
              :id="city.id"
              :name="city.name"
            ></city>
          </div>
        </div>
      </div>
    </section>
    <base-dialog v-if="isRegistration">
    <template v-slot:header>
        <h5 class="modal-title" id="exampleModalLabel">Create Account</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeRegistration">
        X
        </button>
           </template>
      <template v-slot:body>
      <keep-alive>
       <form>
        <div class="modal-body">
         <div class="form-group">
            <label for="username">Username</label>
            <input type="username" class="form-control" id="username" aria-describedby="usernameHelp" placeholder="Enter username" v-model="username">
            <small id="usernameHelp" class="form-text text-muted">Your information is safe with us.</small>
          </div>
            <div class="form-group">
            <label for="password1">Password {{ password }}</label>
            <input type="password" class="form-control" id="password1" placeholder="Password" v-model="password">
          </div>
          <div class="form-group">
            <label for="password1">Confirm Password</label>
            <input type="password" class="form-control" id="password2" placeholder="Confirm Password" v-model="confirmPassword">
          </div>
          <div class="form-group">
            <label for="email1">Email address</label>
            <input type="email" class="form-control" id="email1" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">Your information is safe with us.</small>
          </div>
          <div class="form-group">
           <label for="gender">Pol</label>
           <input class="radioRegistration" type="radio" v-model="gender" value="man"/>man
           <input class="radioRegistration" type="radio" v-model="gender" value="woman"/>woman
           <br />
          </div>
          <div class="form-group">
           <label for="dateofbirth">Datum rodjenja </label><br/>
          <input type="date" name="dateofbirth" id="dateofbirth" v-model="dateofbirth">
          </div>
        </div>
        <div class="modal-footer border-top-0 d-flex justify-content-center">
          <button type="submit" class="btn btn-success" @click.prevent="submit">Submit</button>
        </div>
      </form>
      </keep-alive>
      </template>
      </base-dialog>
  </div>
  `,
  name: "Home",
  data() {
    return {
      username: '',
      password: '',
      confirmPassword: '',
      firstname: '',
      lastname: '',
      email: '',
      gender: '',
      dateofbirth: '',
      cities: [
        {
          id: 12,
          name: "London",
        },
        {
          id: 100,
          name: "Novi Sad",
        },
        {
          id: 102,
          name: "Kraljevo",
        },
        {
          id: 110,
          name: "Beograd",
        },
        {
          id: 111,
          name: "Beograd 2",
        },
        {
          id: 113,
          name: "Beograd 3",
        },
      ],
    };
  },
   mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/home.css';
        document.head.appendChild(style);
  },
  computed: {
    isRegistration() {
     return this.$store.getters.isActive;
     }
  },
  methods: {
    closeRegistration() {
      this.$store.commit('closeRegistration');
    },
    submit() {
      console.log( this.$store.getters['registrationModule/module'])
      this.$store.dispatch(
       'registrationModule/register',
        {
          username: this.username,
          password: this.password,
          confirmPassword: this.confirmPassword,
          firstname:  this.firstname,
          lastname: this.lastname,
          email: this.email,
          gender: this.gender,
          dateofbirth: this.dateofbirth,
        }
      );
     
    }
    
  },

});
