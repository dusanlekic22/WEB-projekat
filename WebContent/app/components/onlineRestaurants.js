Vue.component('online-restaurants',{
template:
`   <section>
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
    </section>`,

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
    }
});
