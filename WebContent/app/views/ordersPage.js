Vue.component('orders-page', {
   template:
      `<div class="container-fluid">
    <div id="restaurantPageSection">
       <div class="gradeAndSearch">
          <div class="container pt-4">
             <div class="d-flex ">
                <img src="img/1.gif">
                <div class="ml-auto">
                   <div id="articleSearch">
                      <input type="search" /> 
                   </div>
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
                <base-order v-for="o in orders" v-if="o.logicalDeleted!== 1" :key="o.id" :order="o" :id="o.id" :restId="o.restaurantId" :status="o.status"
                   :price="o.price" :customerName="o.customerName" :customerSurname="o.customerSurname" @update="updateOrder"></base-order>
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
         user: null,
         restaurant: null
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
      }
   },
   computed: {
      ordersComp() {
         return this.orders = this.$store.getters['ordersModule/orders'];
      },
      isCustomer() {
         return this.$store.getters['userModule/isCustomer'];
      },
      checkRestaurants() {
         return this.restaurant = this.$store.getters['restaurantsModule/restaurant'];;
      },
      isManager(){
          return  this.$store.getters['ordersModule/isManager'];
       }
   }
});