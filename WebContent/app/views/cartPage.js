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
           :price="a.article.price" @dodaj="noviArtikal" @ukloni="ukloniArtikal"> </base-article>
          </div>
                  <div class="col-md-2" >
                  <button @click="poruci">Poruci</button>
                  Ukupan racun: {{  suma }}
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
        art: [],
        mapaKorpa: null,
        suma: 0,
        stari: new Map(),
        restaurantId : null,
        user:null,
        cartId:null
        
      };
    },
    mounted() {
      this.cartId = this.$store.getters['userModule/user'].cartId;
      let style = document.createElement('link');
      style.type = "text/css";
      style.rel = "stylesheet";
      style.href = 'css/restaurantPage.css';
      document.head.appendChild(style);
      this.getCartArticles();
    },
    computed: {
     
      
      cartArticles() {
        this.articles = this.$store.getters['cartModule/articles'];
        console.log("artikli su" + this.articles);
        this.art = [];
        this.articles.forEach((key, value) => {
            this.art.push({ 'article': JSON.parse(value), 'brojPorucenih': key });
          this.restaurantID = JSON.parse(value).restaurantId;
        });

       let sumica = 0;
       this.articles.forEach((key, value) => {
         sumica += JSON.parse(value).price * key;
           this.korpa.push({
             "id": JSON.parse(value).id,
             "brojPorucenih": key
           });
       });
        console.log(sumica);
        this.suma = sumica;

        return this.articles;
      },
      cartComp() {
        return this.cart = this.$store.getters['cartModule/cart'];
      },

    },
    methods: {
      checkId(val){
        console.log(val);
        let k;
        this.articles.forEach((key,value)=> {if(val===JSON.parse(value)){k=key;}});
        return k;
      },
      overallSum() {
            this.suma = 0;
          let s= 0;
          this.mapaKorpa.forEach((values, keys) => {
            s = 0;
           this.art.forEach(element => {
             console.log("elemid" + element.article.id);
              console.log("kljuc" + keys);
            if (element.article.id === keys) {
             s =  values * element.article.price;
            }
           });
            this.suma += s;
          });
        return this.suma;
      },
      noviArtikal(value) {
        if (this.korpa.length === 0) {
          this.korpa.push(value);
        }
        else {
          var notInlist = true;
          this.korpa.forEach(element => {
            if (element.id === value.id) {
              this.changeCart(element.id, value.brojPorucenih);
              this.korpa[this.korpa.indexOf(element)] = value;
              notInlist = false;
            }
          });
          if (notInlist) {
            this.korpa.push(value);
          }
        }
           let map = new Map();
          console.log(this.korpa);
          for (const [key, value] of Object.entries(this.korpa)) {
          console.log(`${value.id}: ${value.brojPorucenih}`);
          //map[value.id] = value.brojPorucenih;
          map.set(value.id, value.brojPorucenih);
      }
          this.mapaKorpa = map;
          console.log(map);
          this.overallSum();
    
      },
      ukloniArtikal(value) {
      if (this.korpa.length === 0) {}
      else {
        this.korpa.forEach(element => {
          if (element.id === value.id) {
            if (value.brojPorucenih === 0) {
              this.changeCart(element.id, 0);
              this.korpa.splice(this.korpa.indexOf(element), 1);
              // const indeks = this.art.indexOf({ 'article': element, 'brojPorucenih': value.brojPorucenih });
              // console.log("indeks jee" + indeks);
              // this.art.splice(indeks+1, 1);
            }
            else {
               this.changeCart(element.id, value.brojPorucenih);
               this.korpa[this.korpa.indexOf(element)] = value;
            }
          }
        });
              let map = new Map();
      console.log(this.korpa);
      for (const [key, value] of Object.entries(this.korpa)) {
        console.log(`${value.id}: ${value.brojPorucenih}`);
        //map[value.id] = value.brojPorucenih;
        map.set(value.id, value.brojPorucenih);
      }
          this.mapaKorpa = map;
           console.log(this.korpa);
          this.overallSum();
    
      }
      },
      poruci() {
        this.activeCartId = this.$store.getters['cartModule/activeCart'];
        console.log("porucio "+ this.activeCartId);
        if (this.activeCartId !== -1) {  
          console.log("PORUCIO");
          this.$store.dispatch('ordersModule/addOrder',
            {
              "cartId": this.cartId,
              "cartPrice": this.suma
            })
        }
        this.art = this.getCartArticles();
      },
      getCartArticles() {
        this.$store.dispatch('cartModule/getCartArticles');
      },
      changeCart(id, value) {
        console.log("IDDD" + id);
        console.log("Value" + value);
        console.log(typeof value);
        this.$store.dispatch('cartModule/changeCart',
          {
            "id": id,
            "quantity": parseInt(value)
          }
        );
                  
      }
    }
  });