Vue.component('orders-page', {
   template:
      `<div class="container-fluid">
    <div id="restaurantPageSection">
       <div class="gradeAndSearch">
          <div class="container pt-4">
             <div class="d-flex ">
                <img src="img/1.gif">
                <div class="ml-auto">
                <div>
                <label for="start">Od:</label>
                      <input type="date" id="start" v-model="startDate" /> 
                </div>
                <div>
                <label for="end"> Do:</label>
                      <input type="date" id="end" v-model="endDate"/> 
                </div>
                <div id="minPrice">
                <label for="min">Min cena:</label>
                      <input type="number" id="min" v-model="minPrice" placeholder="Min cena"/> 
                   </div>
                   <div id="maxPrice">
                   <label for="max">Max cena:</label>
                      <input type="number" id="max" v-model="maxPrice" placeholder="Max cena"/> 
                   </div>
                   <div v-if="checkRestaurants">
                   <select v-model="restType" >
                   <option v-for="r in restaurants">{{r.type}}</option>
                 </select> 
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
       <base-dialog v-if="showCommentForm">
       <template v-slot:header>
           <h5 class="modal-title" id="exampleModalLabel">Ocenjivanje porudzbine </h5>
           <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeCommentForm">
           X
           </button>
              </template>
         <template v-slot:body>
         <keep-alive>
          <form>
           <div class="modal-body">
            <div class="form-group">
               <textarea v-model="formComment" placeholder="Unesite vas komentar"></textarea>
             </div>
               <div class="form-group">
               <label for="formGrade">Ocena</label>
               <input type="number" min="0" max="5" class="form-control" id="formGrade" placeholder="" v-model="formGrade">
               </div>
           <div class="modal-footer border-top-0 d-flex justify-content-center">
             <button type="submit" class="btn btn-success" @click.prevent="addComment">Posalji</button>
             </div>
           </div>
         </form>
         </keep-alive>
         </template>
         </base-dialog>
    </div>
    <div v-if= "ordersComp.length!==0" id="restaurantPageArticle">
       <div class="container pt 2">
          <div class="row ">
             <div class="col-md-2"> </div>
             <div  class="col-md-8">
                <base-order v-for="o in filteredOrders" v-if="o.logicalDeleted!== 1" :key="o.id" :order="o" :id="o.id" :restId="o.restaurantId" :status="o.status" :orderDate="getOrderDate(o)"
                   :price="o.price" :customerName="o.customerName" :customerSurname="o.customerSurname" @update="updateOrder" @comment="leaveComment" @sendRestaurant="getRestaurant"></base-order>
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
         filteredOrders: [],
         minPrice: null,
         maxPrice: null,
         user: null,
         restaurant: null,
         sortBy: 'customerName',
         sortDirection: 'desc',
         searchBar: null,
         startDate: null,
         endDate: null,
         restaurants: [],
         restType: null,
         formComment: '',
         formGrade: '',
         showCommentForm: false,
         commentOrder: null
      };
   },
   mounted() {
      this.getRestaurants;
      let style = document.createElement('link');
      style.type = "text/css";
      style.rel = "stylesheet";
      style.href = 'css/restaurantPage.css';
      document.head.appendChild(style);
      this.getOrders();
      this.user = this.$store.getters['userModule/user'];     
   },
   methods: {
      getOrderDate(order) {
         if (order.dateAndTime != null) {
            var dd = String(order.dateAndTime.dayOfMonth);
            var mm = String(order.dateAndTime.monthValue);
            var yyyy = String(order.dateAndTime.year);
            return today = dd + '-' + mm + '-' + yyyy;
         }
      },
      getCurrentDate() {
         var today = new Date();
         var dd = String(today.getDate()).padStart(2, '0');
         var mm = String(today.getMonth() + 1).padStart(2, '0');
         var yyyy = today.getFullYear();

         return today = yyyy + '-' + mm + '-' + dd;
      },
      getUser(){
         this.user = this.$store.getters['userModule/user'];      
      },
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
      getRestaurant(value) {
         this.restaurant = value.restaurant;
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
         if (this.searchBar !== null)
            this.filteredOrders = items.filter(order => {
               return (this.restaurant.name.toLowerCase().includes(this.searchBar.toLowerCase()));
            });
         if (this.minPrice !== null) {
            this.filteredOrders = this.filteredOrders.filter(order => {
               return order.price > this.minPrice;
            });
         }
         if (this.maxPrice != null) {
            this.filteredOrders = this.filteredOrders.filter(order => {
               return order.price < this.maxPrice;
            });
         }
         if (this.restType) {
            this.filteredOrders = this.filteredOrders.filter(order => {
               return this.restaurnt.name === this.restType;
            });
         }
         if (this.startDate !== null) {
            let dateEntered = new Date(this.startDate);
            this.filteredOrders = this.filteredOrders.filter(order => {
               if (order.dateAndTime != null) {
                  if (order.dateAndTime.year < dateEntered.getFullYear()) {
                     return false;
                  } else if (order.dateAndTime.monthValue < dateEntered.getMonth()) {
                     return false;
                  } else if (order.dateAndTime.dayOfMonth < dateEntered.getDay()) {
                     return false;
                  } else {
                     return true;
                  }
               }
               else return true;
            });
         }
         if (this.endDate !== null) {
            let dateEntered = new Date(this.endDate);
            this.filteredOrders = this.filteredOrders.filter(order => {
               if (order.dateAndTime != null) {
                  if (order.dateAndTime.year > dateEntered.getFullYear()) {
                     return false;
                  } else if (order.dateAndTime.monthValue > dateEntered.getMonth()) {
                     return false;
                  } else if (order.dateAndTime.dayOfMonth > dateEntered.getDay()) {
                     return false;
                  } else {
                     return true;
                  }
               }
               else return true;
            });
         }
      },
      sort(s) {
         if (s === this.sortBy) {
            this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
         }
         this.sortBy = s;
      },
      leaveComment(value){
         this.showCommentForm = true;
         this.commentOrder = value.order;
      },
      closeCommentForm(){
         this.showCommentForm = false;
         this.commentOrder = null;
      },
      addComment(){
         this.commentOrder.commented = 1;
         this.$store.dispatch('ordersModule/updateOrder',
            { "orderId": this.commentOrder.id, "order":  this.commentOrder }).then(
                  axios.post('rest/comments/addComment',
                  {
                     "user": this.user,
                     "restaurantId": this.commentOrder.restaurantId,
                     "text": this.formComment,
                     "rating": this.formGrade,
                     "status": "NOT_APPROVED"
                  })
            );
      }
   },
   computed: {
      ordersComp() {
         this.orders = this.$store.getters['ordersModule/orders']
         return this.filteredOrders = [...this.orders];
      },
      isCustomer() {
         return this.$store.getters['userModule/isCustomer'];
      },
      isDelivery() {
         return this.$store.getters['userModule/isDelivery'];
      },
      checkRestaurants() {
         return this.restaurants = this.$store.getters['restaurantsModule/restaurants'];
      },
      isManager() {
         return this.$store.getters['ordersModule/isManager'];
      },
      getRestaurants() {
         this.$store.dispatch('restaurantsModule/getRestaurants');
      },
   }
});