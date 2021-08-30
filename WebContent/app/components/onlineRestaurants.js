Vue.component('online-restaurants', {
template:
`
<section>
      <div id="onlineRestaurantsSection">
        <div class="container py-5">
          <div class="row py-4">
            <div class="col-lg-12 pt-5 text-center">
            <base-online-restaurant></base-online-restaurant>
            <base-online-restaurant></base-online-restaurant>
            <base-online-restaurant></base-online-restaurant>
            <base-online-restaurant></base-online-restaurant>
            <base-online-restaurant></base-online-restaurant>
            <base-online-restaurant></base-online-restaurant>
            </div>
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
      "Beograd, Srbija",
      "Novi Sad, Srbija",
      "Novi Bečej, Srbija",
      "Novi Pazar, Srbija",
      "Kraljevo, Srbija",
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
