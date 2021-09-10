Vue.component('restaurant-page', {
  template:
    `
 <div class="container-fluid">
	<div id="restaurantPageSection">
		<div class="container py-5">
			<div class="row py-4">
				<div class="col-lg-7 pt-5 text-center restaurant">
					<h1 class="pt-5">
          <div class="white" v-if= "restaurantComp !== null" ><h2> {{restaurant.name}} </h2></div>
          </h1> </div>
			</div>
		</div>
		<div class="gradeAndSearch">
			<div class="container pt-4">
				<div class="d-flex "> <img src="img/1.gif">
        
					<div id="grade">
						<h3>9.6<sup>od 10</sup></h3> </div>
					<div class="ml-auto">
						<div id="articleSearch">
							<input type="search" /> </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div v-if= "restaurantArticles.length !== 0" id="restaurantPageArticle">
		<div class="container pt 2">
			<div class="row ">
				<div class="col-md-2"> </div>
				<div  class="col-md-8"> 
		     <base-article v-for="a in this.articles" v-if="a.logicalDeleted!== 1" :key="a.id" :ida="a.id" :name="a.name" :description="a.description"
         :price="a.price" :isManagerRestaurant="isManagerOfRestaurant" @dodaj="noviArtikal" @ukloni="ukloniArtikal"></base-article>
        </div>
				<div class="col-md-2"> <div><h3> {{restaurant.type}} </h3></div>
        <div><h3> {{restaurant.status}} </h3></div>
         </div>
			</div>
		</div>
    <button @click="addArticlesToCart">Idi na kolica</button>
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
      role: '',
      mapaKorpa: new Map(),
      articles: [],
      korpa: [],
      restaurant: [],
      activeCartId: -1,
      isManagerOfRestaurant: false
    };
  },
  mounted() {
    let style = document.createElement('link');
    style.type = "text/css";
    style.rel = "stylesheet";
    style.href = 'css/restaurantPage.css';
    document.head.appendChild(style);
    this.isRestaurantManager();
    this.getRestaurant();
    this.getRestaurantArticles();
    this.getCartRestaurantId();
  },
  computed: {
    restaurantArticles() {
      return this.articles = this.$store.getters['restaurantArticlesModule/articles'];
    },
    restaurantComp() {
      return this.restaurant = this.$store.getters['restaurantModule/restaurant'];
    }
  },
  methods: {
    noviArtikal(value) {
      if (this.korpa.length === 0) {
        this.korpa.push(value);
      }
      else {
        var notInlist = true;
        this.korpa.forEach(element => {
          if (element.id === value.id) {
            this.korpa[this.korpa.indexOf(element)] = value;
            notInlist = false;
          }
        });
        if (notInlist) {
          this.korpa.push(value);
        }
      }
      let map = new Map();
      console.log(this.korpa);
      for (const [key, value] of Object.entries(this.korpa)) {
        console.log(`${value.id}: ${value.brojPorucenih}`);
        map[value.id] = value.brojPorucenih;
      }
      this.mapaKorpa = map;
      console.log("mapaKorpa" + this.mapaKorpa);
    },
    ukloniArtikal(value) {
      if (this.korpa.length === 0) {}
      else {
        this.korpa.forEach(element => {
          if (element.id === value.id) {
            if (value.brojPorucenih === 0) {
              this.korpa.splice(this.korpa.indexOf(element), 1);
            }
            else {
               this.korpa[this.korpa.indexOf(element)] = value;
            }
          }
        });
      }
      //let map = new Map();
      console.log(this.korpa);
      // for (const [key, value] of Object.entries(this.korpa)) {
      //   console.log(`${value.id}: ${value.brojPorucenih}`);
      //   map[value.id] = value.brojPorucenih;
      // }

    },
    dodajUKorpu() {
      this.$store.dispatch(
        'cartModule/addToCart',
        {
          "map": this.mapaKorpa,
          "id" : this.$route.params.id
        }
      );
    },
    getRestaurantArticles() {
      this.$store.dispatch('restaurantArticlesModule/getRestaurantArticles',
        { "restaurantId": this.$route.params.id });
    },
    getRestaurant() {
      this.$store.dispatch('restaurantModule/getRestaurant',
        { "restaurantId": this.$route.params.id });
    },
     getCartRestaurantId() {
        this.$store.dispatch('cartModule/getCartRestaurantId');
      },
    
    addArticlesToCart() {
      //Fali za dodavanje
      this.activeCartId = this.$store.getters['cartModule/activeCart'];
      console.log("aktivan" +  this.activeCartId);
      if (this.activeCartId === parseInt(this.$route.params.id) ) {
        this.dodajUKorpu();
        setTimeout(() => {
             this.$router.push('/Cart/1');
        }, 500);
        //this.$router.push('/Cart/1');
      }
      else if (this.activeCartId === -1) {
        this.$store.commit('cartModule/setActiveCart', this.$route.params.id);
        this.dodajUKorpu();
       setTimeout(() => {
             this.$router.push('/Cart/1');
        }, 500);
      }
      else {
        // greska
        var r = confirm("Oslobodite aktivnu korpu da bi ste narucili iz novog restorana");
        if (r == true) {
           this.$router.push('/Cart/1');
          }
      }
      },
      isRestaurantManager(){
        this.isManagerOfRestaurant = (parseInt(this.$route.params.id) === parseInt(this.$store.getters['userModule/user'].restaurantId));
        console.log("Gleda menadzer" +this.isManagerOfRestaurant);
      }
  },


});
