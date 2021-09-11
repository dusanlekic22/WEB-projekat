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
                <base-order v-for="o in orders" v-if="o.logicalDeleted!== 1" :key="o.id" :order="o" :id="o.id" :restId="o.restaurantId" :status="o.status"
                   :price="o.price" :customerName="o.customerName" :customerSurname="o.customerSurname" @update="updateOrder" @comment="leaveComment"></base-order>
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
         restaurant: null,
         formComment: '',
         formGrade: '',
         showCommentForm: false,
         commentOrder: null
      };
   },
   mounted() {
      let style = document.createElement('link');
      style.type = "text/css";
      style.rel = "stylesheet";
      style.href = 'css/restaurantPage.css';
      document.head.appendChild(style);
      this.getOrders();
      this.user = this.$store.getters['userModule/user'];     
   },
   methods: {
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