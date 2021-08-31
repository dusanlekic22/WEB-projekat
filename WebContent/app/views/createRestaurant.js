Vue.component('create-restaurant', {
  template:
  `
  <div class="container-fluid">
  <router-view></router-view>
   <div class="container">
   <keep-alive>
       <form>
        <div class="modal-body">
         <div class="form-group">
            <label for="restaurantName">Ime restorana </label>
            <input type="restaurantName" class="form-control" id="restaurantName" aria-describedby="restaurantNameHelp" placeholder="Enter restaurant name" v-model="restaurantName">
            <small id="restaurantNameHelp" class="form-text text-muted">Your information is safe with us.</small>
          </div>
            <div class="form-group">
            <label for="restaurantType">Tip restorana </label>
            <input type="restaurantType" class="form-control" id="restaurantType" placeholder="Enter restaurant type" v-model="restaurantType">
          </div>
          <div class="form-group">
          <label for="restaurantLocation">Lokacija restorana </label>
            <input type="restaurantLocation" class="form-control" id="restaurantLocation" placeholder="Unesite lokaciju restorana" v-model="restaurantLocation">
           <br />
          </div>
            <div class="form-group">
          <label for="restaurantImage">Slika restorana </label>
          <div class="imageRestaurant">
            <img :src="restaurantImage" >
          </div>
           <input type="file"  @change=uploadImage id="file-input" v-model="restaurantImage">
           <br />
          </div>


        </div>
        <div class="modal-footer border-top-0 d-flex justify-content-center">
          <button type="submit" class="btn btn-success" @click.prevent="submit">Submit</button>
        </div>
      </form>
    </keep-alive>
      </div>
  </div>
  `,
  name: "Home",
  data() {
      return {
      restaurantName: '',
      restaurantType: '',
      restaurantLocation: '',
      restaurantImage: null,
      restaurantManager: null,
    };
  },
   mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/createRestaurant.css';
       document.head.appendChild(style);
  },
  computed: {
  },
    methods: {
      submit() {
      this.$store.dispatch(
       'registrationModule/addManagerDeliver',
        {
          username: this.username,
          password: this.password,
          confirmPassword: this.confirmPassword,
          name:  this.firstname,
          surname: this.surname,
          gender: this.gender,
          dateOfBirth: this.dateofbirth,
          role: this.role
        }
      );
      },
      uploadImage(e) {
           this.restaurantImage = '';
           const image = e.target.files[0];
            console.log(e.target.files[0]);
                const reader = new FileReader();
                reader.readAsDataURL(image);
                reader.onload = e =>{
                    this.restaurantImage = e.target.result;
                    console.log(this.restaurantImage);
                };
            }
  },

});
