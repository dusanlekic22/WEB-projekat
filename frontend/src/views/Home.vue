<template>
  <div class="container-fluid">
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
      <h2>Istraži gradove u kojima ćeš naći MFOOD</h2>
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
</template>

<script>
import City from "../components/City.vue";
import Places from "../components/Places.vue";
export default {
  components: {
    City,
    Places,
  },
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
};
</script>

<style scoped>
#searchSection {
  display: flex;
  height: 800px;
  width: 100%;
  background: url("../img/lily-banse--YHSwy6uqvk-unsplash.jpg") no-repeat;
  background-size: cover;
}
#searchSection h1 {
  font-size: 4rem;
  font-weight: 700;
  text-align: left;
  color: white;
}

h2 {
  font-weight: 800;
  color: black;
}

.container-fluid {
  padding: 0%;
}
.showCountries {
  background: white;
  border: 0;
  font-size: 17px;
  color: rgb(25, 153, 238);
}
</style>
