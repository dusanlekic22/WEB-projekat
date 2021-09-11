Vue.component('base-order', {
  template:
    `
<div class="col-12 comment"> 
 <div class="row">
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ id }}
      </div>
    </div>
      <div v-if="restaurantComp" class="col-6 col-sm-6">
      <div class="comment-header">
        {{ restaurant.name }}
      </div>
    </div>
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ customerName }}
      </div>
    </div>
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ customerSurname }}
      </div>
    </div>
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ status }}
      </div>
    </div>
    <div class="col-6 col-sm-6">
      <div class="comment-header">
        {{ price }}
      </div>
    </div>
      <div v-if="canCancel" class="col-3 col-sm-3">
      <button  @click="updateOrder('CANCELLED')">Otkazi porudzbinu</button>
     </div>
     <div v-if="canPrepare" class="col-3 col-sm-3">
     <button  @click="updateOrder('PREPARING')">Stavi da se priprema</button>
  </div>
     <div v-if="canPutForDelivery" class="col-3 col-sm-3">
     <button  @click="updateOrder('AWAITING_DELIVERY')">Zatrazi dostavljaca</button>
    </div>
    <div v-if="canSendRequest" class="col-3 col-sm-3">
     <button  @click="updateOrder('DELIVERY_REQUESTED')">Zatrazi porudzbinu</button>
    </div>
    <div v-if="canApproveRequest" class="col-3 col-sm-3">
     <button  @click="updateOrder('TRANSPORTING')">Odobri zahtev za dostavu</button>
    </div>
    <div v-if="canApproveRequest" class="col-3 col-sm-3">
     <button  @click="updateOrder('AWAITING_DELIVERY')">Odbij zahtev</button>
    </div>
    <div v-if="canSetDelivered" class="col-3 col-sm-3">
     <button  @click="updateOrder('DELIVERED')">Porudzbina je dostavljena</button>
    </div>
 </div>
</div>
`,

  mounted() {
    this.getRestaurant();
    let style = document.createElement('link');
    style.type = "text/css";
    style.rel = "stylesheet";
    style.href = 'css/baseComment.css';
    document.head.appendChild(style);
  },
  data() {
    return {
      tekstKomentara: 'Solidna dostava',
      ocena: null,
      restaurant:null
    };
  },
  computed: {
    canCancel() {
      return this.status === 'PROCESSING' && this.isCustomer;
    },
    canPrepare() {
      return this.status === 'PROCESSING' && this.isManager;
    },
    canPutForDelivery() {
      return this.isManager && this.status == 'PREPARING';
    },
    isCustomer() {
      return this.$store.getters['userModule/isCustomer'];
    },
    isManager() {
      return this.$store.getters['userModule/isManager'];
    },
    isDelivery() {
      return this.$store.getters['userModule/isDelivery'];
    },
    canSendRequest() {
      return this.isDelivery && this.status === 'AWAITING_DELIVERY';
    },
    canApproveRequest() {
      return this.isManager && this.status === 'DELIVERY_REQUESTED';
    },
    canSetDelivered() {
      return this.isDelivery && this.status === 'TRANSPORTING';
    },
    restaurantComp() {
      return this.restaurant = this.$store.getters['restaurantModule/restaurant'];
    }
  },
  updated() {
    this.sendRestaurant();
  },
  methods: {
    removeOrder() {
      this.$emit('remove', {
        "orderId": this.id
      });
    },
    updateOrder(status) {
      this.$emit('update', {
        "status": status,
        "order": this.order
      });
    },
    sendRestaurant() {
      console.log("rest " + this.restaurant);
      this.$emit('sendRestaurant', {
        "restaurant": this.restaurant
      });
    },
    getRestaurant() {
      this.$store.dispatch('restaurantModule/getRestaurant',
        { "restaurantId": this.restId });
    },
  },
  props: ['id', 'restId', 'price', 'status', 'order','customerName','customerSurname'],
  
});