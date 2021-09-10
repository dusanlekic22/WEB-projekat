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
                <base-order v-for="o in orders" v-if="o.logicalDeleted!== 1" :key="o.id" :id="o.id" :restId="o.restId" :status="o.status"
                   :price="o.price"><div v-if="isCustomer" ><button @click="deleteOrder">Ukloni porudzbinu</button></div></base-order>
               <base-order v-for="o in orders" v-if="o.logicalDeleted!== 1" :key="o.id" :id="o.id" :restId="o.restId" :status="o.status"
                   :price="o.price"><div v-if="isManager" ><button @click="deleteOrder">Ukloni porudzbinu</button></div></base-order>
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
            user: null
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
        this.$store.dispatch('ordersModule/getOrders',
          { "orderId": this.$route.params.id });
      },
      updateOrder() {
        this.$store.dispatch('ordersModule/updateOrder',
          { "orderId": this.$route.params.id });
      },
      deleteOrder() {
        this.$store.dispatch('ordersModule/deleteOrder',
          { "orderId": this.$route.params.id });
      },
   },
   computed:{
      ordersComp() {
         return this.orders = this.$store.getters['ordersModule/orders'];
       },
       isCustomer(){
          return this.user = this.$store.getters['ordersModule/isCustomer'];
      },
      isManager(){
          return this.user = this.$store.getters['ordersModule/isManager'];
       }
   }
});