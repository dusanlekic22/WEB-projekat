<template>
  <div>
    <input type="text" v-model="message" @input="newPlace" />
    <button class="btn1">Pretraži</button>
    <ul v-if="contains">
      <li v-for="(result, i) in foundResults" :key="i">
        {{ result }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: null,
      contains: false,
      searchResults: [
        "Beograd, Srbija",
        "Novi Sad, Srbija",
        "Novi Bečej, Srbija",
        "Novi Pazar, Srbija",
        "Kraljevo, Srbija",
      ],
      foundResults: [],
    };
  },
  methods: {
    newPlace() {
      this.foundResults = [];
      this.searchResults.forEach((element) => {
        if (
          element.split(",")[0].includes(this.message) &&
          this.message !== ""
        ) {
          this.foundResults.push(element);
          this.contains = true;
        }
      });
    },
  },
};
</script>

<style scoped>
.btn1 {
  height: 51px;
  width: 17%;
  border: none;
  outline: none;
  background-color: rgb(25, 153, 238);
  color: white;
  font-weight: 700;
  border-radius: 10px;
  margin-left: 15px;
}
input {
  height: 51px;
  width: 300px;
  border-radius: 10px;
  border: none;
}
input:focus {
  border: 3px solid rgb(25, 153, 238);
  outline: none !important;
}
ul {
  background: white;
  width: 300px;
  text-align: left;
  text-decoration: none;
  margin-left: 152px;
  margin-top: 1px;
}
li {
  list-style-type: none;
}
</style>
