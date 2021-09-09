Vue.component('online-restaurants', {
  template:
    `
<section>
      <div id="onlineRestaurantsSection" >
        <div class="container py-5">
        <input class="restaurantsSearchInput" type="text" placeholder="Ime" v-model="name"/>
        <input class="restaurantsSearchInput" type="text" placeholder="Lokacija" v-model="location"/>
        <select v-model="type" class="restaurantsSearchButton">
          <option v-for="r in onlineRestaurants">{{r.type}}</option>
        </select> 
        <input type="checkbox" class="restaurantsSearchCheckbox" id="checkbox" v-model="open"/>
        <label class="restaurantsSearchLabel" for="checkbox">Otvoren</label>
        <button class="restaurantsSearchButton" @click.prevent = "search">Pretrazi</button>
        <button class="restaurantsSearchButton" @click.prevent = "sortedProducts('name')">Sortiraj po imenu</button>
        <button class="restaurantsSearchButton" @click.prevent = "sortedProducts('status')">Sortiraj po statusu</button>
          <div  v-if= "checkRestaurants.length !== 0" class="row py-4">
            <base-online-restaurant  v-for="r in filteredRestaurants" v-if="r.logicalDeleted !== 1" :key="r.id" :name="r.name" :isOpen="r.status" :type="r.type" :id="r.id"></base-online-restaurant>
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
      sortBy: 'name',
      sortDirection: 'desc',
      onlineRestaurants: [],
      filteredRestaurants: []
    };
  },
  mounted() {
    this.getRestaurants();
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
    this.sortedProducts('status');
  },
  computed: {
    checkRestaurants() {
      this.onlineRestaurants = this.$store.getters['restaurantsModule/restaurants'];
      return this.filteredRestaurants = [...this.onlineRestaurants];
    }

  },
  methods: {
    sortedProducts(sortBy) {
      this.onlineRestaurants = this.$store.getters['restaurantsModule/restaurants'];
      this.filteredRestaurants = [...this.onlineRestaurants];
      this.filteredRestaurants.sort((p1, p2) => {

        let modifier = 1;
        if (this.sortDirection === 'desc') modifier = -1;

        if (sortBy === 'status') {
          let modifier = -1;
          if (this.sortDirection === 'desc') modifier = 11;
        }
        if (p1[sortBy] < p2[sortBy]) return -1 * modifier; if (p1[sortBy] > p2[sortBy]) return 1 * modifier;
        return 0;
      });
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    },
    search() {
      let articles = [...this.onlineRestaurants];
      this.filteredRestaurants = articles.filter(restaurant => {
        return (restaurant.name.toLowerCase().includes(this.name.toLowerCase()) &&
          restaurant.location.address.city.toLowerCase().includes(this.location.toLowerCase()));
      });
      if (this.type !== null) {
        this.filteredRestaurants = this.filteredRestaurants.filter(restaurant => {
          return (restaurant.type.toLowerCase().includes(this.type.toLowerCase()));
        });
      }
      if (this.open) {
        this.filteredRestaurants = this.filteredRestaurants.filter(restaurant => {
          return restaurant.status === "OPEN";
        });
      }
    },
    sort: function (s) {
      if (s === this.sortBy) {
        this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
      }
      this.sortBy = s;
    },
    getRestaurants() {
      this.$store.dispatch('restaurantsModule/getRestaurants');
    },
  }
});
