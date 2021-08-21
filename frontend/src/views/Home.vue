<template>
  <div class="container-fluid">
    <section>
      <div id="searchSection"></div>
    </section>
    <section class="mt-5 cards-section">
      <div class="container">
        <div class="row">
          <city
            v-for="city in cities"
            :key="city.id"
            :id="city.id"
            :name="city.name"
          ></city>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import City from "../components/City.vue";
export default {
  components: {
    City,
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
  background-image: url("../img/lily-banse--YHSwy6uqvk-unsplash.jpg");
  background-size: cover;
}
.container-fluid {
  padding: 0%;
}
</style>
