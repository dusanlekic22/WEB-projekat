Vue.component('restaurant-page', {
  template:
  `
  <div class="container-fluid">
  <div id="restaurantPageSection">
        <div class="container py-5">
          <div class="row py-4">
            <div class="col-lg-7 pt-5 text-center restaurant">
              <h1 class="pt-5">
                Želiš da isprobaš nešto novo?
              </h1>
              <places></places>
            </div>
          </div>
        </div>
  <div class="gradeAndSearch">
    <div class="container pt-4">
    <div class="d-flex ">
    <img src="img/1.gif">
    <div id="grade">
    <h3>9.6<sup>od 10</sup></h3>
    </div>
      <div class="ml-auto">
        <div id="articleSearch">
            <input type="search"/>
        </div>
      </div>
    </div>
 </div>
  </div>
  </div>
   <div class="container">
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
      role: ''
    };
  },
   mounted() {
        let style = document.createElement('link');
        style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/restaurantPage.css';
       document.head.appendChild(style);
  },
  computed: {
  },
  methods: {
  
    },

});
