Vue.component('profile', {
  template:
  `
  <div class="container-fluid">
   <div class="container">
       <form>
        <div class="modal-body">
         <div class="form-group">
            <label for="username">Username : {{ username }}</label>
            <input type="username" class="form-control" id="username" aria-describedby="usernameHelp" :placeholder="username" v-model="username">
            <small id="usernameHelp" class="form-text text-muted">Your information is safe with us.</small>
          </div>
            <div class="form-group">
            <label for="password1">Password {{ password }}</label>
            <input type="password" class="form-control" id="password1" :placeholder="password" v-model="password">
          </div>
          <div class="form-group">
            <label for="password1">Confirm Password {{ confirmPassword }}</label>
            <input type="password" class="form-control" id="password2" :placeholder="confirmPassword" v-model="confirmPassword">
          </div>
           <div class="form-group">
            <label for="firstname">Name {{ firstname }}</label>
            <input type="firstname" class="form-control" id="firstname" aria-describedby="firstnameHelp" :placeholder="firstname" v-model="firstname">
            <small id="firstnameHelp" class="form-text text-muted">Your information is safe with us.</small>
          </div>
          <div class="form-group">
            <label for="surname">Surname {{ surname }}</label>
            <input type="surname" class="form-control" id="surname" :placeholder="surname" v-model="surname">
          </div>
          <div class="form-group">
           <label for="gender">Pol</label>
           <input class="radioRegistration" type="radio" v-model="gender" value="MALE"/>MALE
           <input class="radioRegistration" type="radio" v-model="gender" value="FEMALE"/>FEMALE
           <br />
          </div>
          <div class="form-group">
           <label for="dateofbirth">Datum rodjenja {{ dateofbirth }} </label><br/>
          <input type="date" name="dateofbirth" id="dateofbirth" v-model="dateofbirth">
          </div>
        </div>
        <div class="modal-footer border-top-0 d-flex justify-content-center">
          <button type="submit" class="btn btn-success" @click.prevent="submit">Submit</button>
        </div>
      </form>
      </div>
  </div>
  `,
  name: "Home",
  data() {
      return {
      username: '',
      password: '',
      confirmPassword: '',
      firstname: '',
      surname: '',
      gender: '',
      dateofbirth: '',
      loginUsername: '',
      loginPassword: '',
    };
  },
   mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/profile.css';
       document.head.appendChild(style);
       
       this.username = this.$store.getters['userModule/user'].username;
       this.password = this.$store.getters['userModule/user'].password;
       this.confirmPassword = this.$store.getters['userModule/user'].confirmPassword;
       this.dateofbirth = this.$store.getters['userModule/user'].dateofbirth;
       this.firstname = this.$store.getters['userModule/user'].first;
       this.surname = this.$store.getters['userModule/user'].surname;
       this.gender = this.$store.getters['userModule/user'].gender;

  },
  computed: {
  },
  methods: {
  },

});
