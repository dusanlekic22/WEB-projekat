Vue.component('cart-page', {
    template:
      `
   <div class="container-fluid">
      <div v-if="cartComp">
          <h1>Moj Cart</h1>
      </div>
      <div id="restaurantPageArticle">
          <div class="container pt 2">
              <div class="row ">
                  <div class="col-md-2"> </div>
                  <div v-if= "cartArticles.length !== 0" class="col-md-8"> 
               <base-article v-for="a in art" :key="a.article.id" v-if="a.article.logicalDeleted!== 1" :ida="a.article.id" 
               :name="a.article.name" :description="a.article.description" :quantity="a.brojPorucenih"
           :price="a.article.price" @dodaj="noviArtikal"> </base-article>
          </div>
                  <div class="col-md-2"> 
           </div>
              </div>
          </div>
      </div>
  </div>
    `,
    name: "Home",
    data() {
      return {
        username: '',
        password: '',
        confirmPassword: '',
        firstname: '',
        surname: '',
        gender: '',
        dateofbirth: '',
        loginUsername: '',
        loginPassword: '',
        role: '',
        articles: new Map(),
        korpa: [],
        cart:null,
        art:[],
      };
    },
    mounted() {
      let style = document.createElement('link');
      style.type = "text/css";
      style.rel = "stylesheet";
      style.href = 'css/restaurantPage.css';
      document.head.appendChild(style);
      this.getCart();
      this.getCartArticles();
    },
    computed: {
      
      
      cartArticles() {
        this.articles = this.$store.getters['cartModule/articles'];
        console.log(this.articles);
        this.articles.forEach((key,value)=> {this.art.push({'article' :JSON.parse(value),'brojPorucenih':key})});
        return this.articles = this.$store.getters['cartModule/articles'];
      },
      cartComp() {
        return this.cart = this.$store.getters['cartModule/cart'];
      }
    },
    methods: {
      checkId(val){
        console.log(val);
        let k;
        this.articles.forEach((key,value)=> {if(val===JSON.parse(value)){k=key;}});
        return k;
      },
      noviArtikal(value) {
        if (this.korpa.length === 0) {
          this.korpa.push(value);
        }
        else {
          var notInlist = true;
          this.korpa.forEach(element => {
            if (element.id === value.id) {
              this.korpa[this.korpa.indexOf(element)] = value;
              notInlist = false;
            }
          });
          if (notInlist) {
            this.korpa.push(value);
          }
  
        }
        // this.korpa.push(value);
        console.log(this.korpa);
      },
      getCartArticles() {
        this.$store.dispatch('cartModule/getCartArticles',
          { "cartId": 1 });
      },
      getCart() {
        this.$store.dispatch('cartModule/getCart',
          { "cartId": 1 });
      },
    },
  
  
  });
  