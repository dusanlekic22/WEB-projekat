Vue.component('online-restaurants', {
  template:
    `
<section>
      <div id="onlineRestaurantsSection" >
        <div class="container py-5">
        <input type="text" placeholder="Ime" v-model="name"/>
        <input type="text" placeholder="Lokacija" v-model="location"/>
        <select v-model="type">
          <option v-for="r in onlineRestaurants">{{r.type}}</option>
        </select> 
        <input type="checkbox" id="checkbox" v-model="open"/>
        <label for="checkbox">Otvoren</label>
        <button @click.prevent = "search">Pretrazi</button>
          <div  v-if= "checkRestaurants.length !== 0" class="row py-4">
            <base-online-restaurant  v-for="r in filteredRestaurants"  :key="r.id" :name="r.name"></base-online-restaurant>
            </div>
        </div>
      </div>
    </section>  
`,

  data() {
    return {
      message: null,
      contains: false,
      name: '',
      location: '',
      type: null,
      open: null,
      restaurants: [
        {
          id: 1,
          name: "Boom Boom"
        },
        {
          id: 2,
          name: "Ciao Ciao"
        },
        {
          id: 3,
          name: "Zar Mance"
        }
      ],
      onlineRestaurants: [],
      filteredRestaurants: []
    };
  },
  mounted() {
    this.getRestaurants();
    console.log(this.onlineRestaurants);
    let style = document.createElement('link');
    style.type = "text/css";
    style.rel = "stylesheet";
    style.href = 'css/onlineRestaurants.css';
    document.head.appendChild(style);

    $('.multi-item-carousel').on('slide.bs.carousel', function (e) {
      let $e = $(e.relatedTarget),
        itemsPerSlide = 3,
        totalItems = $('.carousel-item', this).length,
        $itemsContainer = $('.carousel-inner', this),
        it = itemsPerSlide - (totalItems - $e.index());
      if (it > 0) {
        for (var i = 0; i < it; i++) {
          $('.carousel-item', this).eq(e.direction == "left" ? i : 0).
            // append slides to the end/beginning
            appendTo($itemsContainer);
        }
      }
    });

  },
  computed: {
    checkRestaurants() {
      this.onlineRestaurants = this.$store.getters['restaurantsModule/restaurants'];
      return this.filteredRestaurants = [...this.onlineRestaurants];
    },

  },
  methods: {
    inital() {

    },
    search() {
      let articles = [...this.onlineRestaurants];
      this.filteredRestaurants = articles.filter(restaurant => {
        return (restaurant.name.toLowerCase().includes(this.name.toLowerCase()) &&
          restaurant.location.address.city.toLowerCase().includes(this.location.toLowerCase())); 
      });
      if(this.type !== null){
      this.filteredRestaurants = this.filteredRestaurants.filter(restaurant => {
        return (restaurant.type.toLowerCase().includes(this.type.toLowerCase())); 
      });
    }
      if(this.open){
        this.filteredRestaurants = this.filteredRestaurants.filter(restaurant => {
          return restaurant.status === "OPEN"; 
        });
      }
      console.log(this.filteredRestaurants);
    },
    getRestaurants() {
      this.$store.dispatch('restaurantsModule/getRestaurants');
    },
  }
});
