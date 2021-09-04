Vue.component('create-restaurant', {
  template:
  `
  <div class="container-fluid">
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
            <div class="form-group"  v-if="freeManager.length !== 0">
          <select v-model="manager" >
            <option v-for="m in freeManagers" value="m"> {{ m.name }} {{ m.surname }} </option>
          </select>
          </div>
          <div class="form-group" v-else>
          	<button @click="dodajMenadzera" v-if="!needManager">Dodaj menadzera</button>  
          	<router-view></router-view>
          </div>
            <div class="form-group">
          <label for="restaurantImage">Slika restorana </label>
          <div class="imageRestaurant">
            <img :src="restaurantImage" >
          </div>
           <input type="file"  @change=uploadImage id="file-input" ref="file" >
          <br />
          </div>
        </div>
        <div class="modal-footer border-top-0 d-flex justify-content-center"  v-if="!needManager">
          <button type="submit" class="btn btn-success" @click.prevent="dodajRestoran">Submit</button>
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
      freeManagers: [],
      needManager: false,
      manager: ''
    };
  },
   mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/createRestaurant.css';
       document.head.appendChild(style);
       this.getFreeManagers();
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
        this.file = this.$refs.file.files[0];
        console.log('>>>> 1st element in files array >>>> ', this.file);
            },
         getFreeManagers(){
         this.$store.dispatch('managerModule/getFreeManagers');
        },
        dodajMenadzera(){
        this.needManager = true;
        this.$router.push('/createRestaurant/manager');
      },
      dodajRestoran() {
        this.$store.dispatch('restaurantModule/addRestaurantUpdateManager', {
          "name": this.name,
          "type": this.type,
          "location": this.location,
          "image": this.restaurantImage,
          "manager": 1
        });
        
      // let formData = new FormData();
      // formData.append('file', this.file);
      // console.log('>> formData >> ', formData);

      // axios.post('/rest/users/fileupload',
      //     formData, {
      //       headers: {
      //         'Content-Type': 'multipart/form-data'
      //       }
      //     }
      //   ).then(function () {
      //     console.log('SUCCESS!!');
      //   })
      //   .catch(function () {
      //     console.log('FAILURE!!');
      //   });
        }
  },
  computed: {
    freeManager(){
      return this.freeManagers = this.$store.getters['managerModule/freeManagers'];
    }
  }
});
