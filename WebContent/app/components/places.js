Vue.component('places',{
template:
`<div>
  <input type="text" v-model="message" @input="newPlace" />
  <button class="btn1">Pretraži</button>
  <ul v-if="contains">
    <li v-for="(result, i) in foundResults" :key="i">
      {{ result }}
    </li>
  </ul>
</div>`,

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
 mounted() {
        let style = document.createElement('link');
      style.type = "text/css";
        style.rel = "stylesheet";
        style.href = 'css/places.css';
        document.head.appendChild(style);
    }
});
