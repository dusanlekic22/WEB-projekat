Vue.component('online-restaurants', {
template:
`
<section>
      <div id="onlineRestaurantsSection">
        <div class="container py-5">
          <div class="row py-4">
            <base-online-restaurant v-for="r in this.restaurants" :key="r.id" :name="r.name"></base-online-restaurant>
            </div>
        </div>
      </div>
    </section>  
`,

data() { 
  return {
    message: null,
    contains: false,
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
  };
},
 mounted() {
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
  methods: {
      inital() {
          
   }
 }
});
