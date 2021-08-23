Vue.component('home', {
    template:
        ` <div class="container-fluid">
    <section>
      <div id="searchSection">
        <div class="container py-5">
          <div class="row py-4">
            <div class="col-lg-7 pt-5 text-center">
              <h1 class="pt-5">
                Želiš da isprobaš nešto novo?
              </h1>
              <places></places>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="mt-5 cards-section">
    <div class="container">
      <h2>Istraži gradove u kojima ćeš naći MFOOD</h2>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-sm-12 ">
            <h2 class="pt-5">
              🇷🇸 Srbija <br />
              <button class="showCountries">Prikaži sve zemlje</button>
            </h2>
          </div>
          <div class="col-lg-8 col-sm-2">
            <city
              v-for="city in cities"
              :key="city.id"
              :id="city.id"
              :name="city.name"
            ></city>
          </div>
        </div>
      </div>
    </section>
  </div>
  `,
  name: "Home",
  data() {
    return {
      cities: [
        {
          id: 12,
          name: "London",
        },
        {
          id: 100,
          name: "Novi Sad",
        },
        {
          id: 102,
          name: "Kraljevo",
        },
        {
          id: 110,
          name: "Beograd",
        },
        {
          id: 111,
          name: "Beograd 2",
        },
        {
          id: 113,
          name: "Beograd 3",
        },
      ],
    };
  },
   mounted() {
        let style = document.createElement('link');
      style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/home.css';
        document.head.appendChild(style);
    }

});
