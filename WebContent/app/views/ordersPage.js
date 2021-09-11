Vue.component('orders-page', {
   template:
      `<div class="container-fluid">
    <div id="restaurantPageSection">
       <div class="gradeAndSearch">
          <div class="container pt-4">
             <div class="d-flex ">
                <img src="img/1.gif">
                <div class="ml-auto">
                <div id="minPrice">
                <label for="min">Min cena:</label>
                      <input type="number" id="min" v-model="minPrice" placeholder="Min cena"/> 
                   </div>
                   <div id="maxPrice">
                   <label for="max">Max cena:</label>
                      <input type="number" id="max" v-model="maxPrice" placeholder="Max cena"/> 
                   </div>
                   <div id="articleSearch">
                      <input type="search" v-model="searchBar" placeholder="Restoran"/> 
                   </div>
                   <button @click.prevent = "sortedProducts('customerName')">Sortiraj po restoranu</button>
                   <button @click.prevent = "search">Pretrazi</button>
                </div>
             </div>
          </div>
       </div>
    </div>
    <div v-if= "ordersComp.length!==0" id="restaurantPageArticle">
       <div class="container pt 2">
          <div class="row ">
             <div class="col-md-2"> </div>
             <div  class="col-md-8">
                <base-order v-for="o in filteredOrders" v-if="o.logicalDeleted!== 1" :key="o.id" :order="o" :id="o.id" :restId="o.restaurantId" :status="o.status"
                   :price="o.price" :customerName="o.customerName" :customerSurname="o.customerSurname" @update="updateOrder" @sendRestaurant="getRestaurant"></base-order>
             </div>
          </div>
       </div>
    </div>
 </div>
 `,
   name: 'Home',
   data() {
      return {
         orders: [],
         filteredOrders:[],
         minPrice:null,
         maxPrice:null,
         user: null,
         restaurant: null,
         sortBy: 'customerName',
         sortDirection: 'desc',
         searchBar:null
      };
   },
   mounted() {
      let style = document.createElement('link');
      style.type = "text/css";
      style.rel = "stylesheet";
      style.href = 'css/restaurantPage.css';
      document.head.appendChild(style);
      this.getOrders();
   },
   methods: {
      getOrders() {
         this.$store.dispatch('ordersModule/getOrders');
      },
      updateOrder(value) {
         let o = value.order;
         console.log("ORDER " + value.order.status);
         o.status = value.status;
         this.$store.dispatch('ordersModule/updateOrder',
            { "orderId": value.order.id, "order": o });
      },
      getRestaurant(value){
         this.restaurant=value.restaurant;
      },
      sortedProducts(sortBy) {
         console.log("sortiranje");
         this.orders = this.$store.getters['ordersModule/orders'];
         this.filteredOrders = [...this.orders];
         this.filteredOrders.sort((p1, p2) => {

            let modifier = 1;
            if (this.sortDirection === 'desc') modifier = -1;

            if (p1[sortBy] < p2[sortBy]) return -1 * modifier; if (p1[sortBy] > p2[sortBy]) return 1 * modifier;
            return 0;
         });
         this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
      },
      search() {
         let items = [...this.orders];
         console.log("search");
         this.filteredOrders = items.filter(order => {
            return (this.restaurant.name.toLowerCase().includes(this.searchBar.toLowerCase()));
         });
         if (this.minPrice !== null) {
            this.filteredOrders = this.filteredOrders.filter(order => {
               return order.price>this.minPrice;
            });
         }
         if (this.maxPrice!= null) {
            this.filteredOrders = this.filteredOrders.filter(order => {
               return order.price<this.maxPrice;
            });
         }
      },
      sort(s) {
         if (s === this.sortBy) {
            this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
         }
         this.sortBy = s;
      },
   },
   computed: {
      ordersComp() {
         this.orders = this.$store.getters['ordersModule/orders']
         return this.filteredOrders = [...this.orders];
      },
      isCustomer() {
         return this.$store.getters['userModule/isCustomer'];
      },
      checkRestaurants() {
         return this.restaurant = this.$store.getters['restaurantsModule/restaurant'];
      },
      isManager() {
         return this.$store.getters['ordersModule/isManager'];
      }
   }
});