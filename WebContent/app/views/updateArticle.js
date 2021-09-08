Vue.component('update-article', {
  template:
    `
  <div class="container-fluid" v-if="checkArticle !=== null">
   <div class="container">
   <keep-alive>
       <form>
        <div class="modal-body">
         <div class="form-group">
            <label for="articleName">Ime artikla </label>
            <input type="articleName" class="form-control" id="articleName" aria-describedby="articleNameHelp" placeholder="Unesite naziv artikla" v-model="article.articleName">
            <small id="articleNameHelp" class="form-text text-muted">Your information is safe with us.</small>
          </div>
                <div class="form-group">
            <label for="articlePrice">Cena artikla </label>
            <input type="articlePrice" class="form-control" id="articlePrice" placeholder="Unesite cenu artikla" v-model="article.articlePrice">
          </div>
            <div class="form-group">
            <select v-model="articleType" >
            <option value="FOOD"> FOOD </option>
            <option value="DRINK"> DRINK </option>
          </select>
          </div>
          <div class="form-group" v-if="restaurantData !== null">
          <select v-model="article.articleRestaurantId" >
            <option :value="article.articleRestaurantId"> {{ article.articleRestaurantId }} </option>
          </select>
          </div>
          <div class="form-group">
          <label for="articleQuantity">Kolicina artikla </label>
            <input type="articleQuantity" class="form-control" id="articleQuantity" placeholder="Unesite kolicinu artikla" v-model="article.articleQuantity">
           <br />
          </div>
           </div>
            <div class="form-group">
            <label for="articleDescription">Opis artikla </label>
            <input type="articleDescription" class="form-control" id="articleDescription" placeholder="Unesite opis artikla" v-model="article.articleDescription">
          </div>
            <div class="form-group">
          <label for="article.articleImage">Slika restorana </label>
          <div class="imageRestaurant">
            <img :src="article.articleImage" >
          </div>
           <input type="file"  @change=uploadImage id="file-input" ref="file" >
          <br />
          </div>
        <div class="modal-footer border-top-0 d-flex justify-content-center" >
          <button type="submit" class="btn btn-success" @click.prevent="updateArticle">Submit</button>
        </div>
      </form>
    </keep-alive>
      </div>
  </div>
  `,
  name: "UpdateArticle",
  data() {
    return {
      article: null
    };
  },
  mounted() {
    },
  props: ['idArticle'],
  methods: {
    uploadImage(e) {
      this.articleImage = '';
      const image = e.target.files[0];
      console.log(e.target.files[0]);
      const reader = new FileReader();
      reader.readAsDataURL(image);
      reader.onload = e => {
        this.articleImage = e.target.result;
      };
      this.file = this.$refs.file.files[0];
      },
      getArticle() {
       axios.get('rest/articles/'+ this.idArticle,
              articleIdsWithQuantity
          )
              .then(response => {
                  this.message = response.data;
                  console.log("\n\n -------Dodata korpa -------\n");
                  this.article = response.data;
                  console.log("\n\n ----------------------\n\n");
              })
              .catch(err => {
                  console.log("\n\n ------- ERROR -------\n");
                  console.log(err);
                  console.log("\n\n ----------------------\n\n");
              });       
      },
      updateArticle() {
        this.$store.dispatch('restaurantModule/updateArticle', {
        "id": this.idArticle,
        "articleName": this.articleName,
        "articlePrice": this.articlePrice,
        "articleType": this.articleType,
        "articleRestaurantId":  this.articleRestaurantId,
        "articleQuantity": this.articleQuantity,
        "articleDescription": this.articleDescription,
        "articleImage": this.articleImage
      });
    }
  },
  computed: {
    restaurantData() {
      console.log(this.$store.getters['userModule/user'].restaurantId);
      return this.articleRestaurantId = this.$store.getters['userModule/user'].restaurantId;
      },
      checkArticle() {
          return this.article;
  }
  }
});
