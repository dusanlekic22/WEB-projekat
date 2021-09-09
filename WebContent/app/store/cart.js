var cartStore = {
    namespaced: true,
    state() {
        return {
            articles: new Map(),
            cart: null,
            activeCartId: -1
            
        };
    },
    mutations: {
        setCartArticles(state, payload) {
            state.articles = new Map(Object.entries(payload));
        },
        setCart(state, payload) {
            state.cart = payload;
        },
        setActiveCart(state, payload) {
            state.activeCartId = payload;
        }
    },
    actions: {
        getCartArticles(context, payload) {
            axios.get('rest/carts/getCartArticles/'+payload.cartId)
                .then(response => {
                    console.log("\n\n -------Artikli kolica-------\n");
                    context.commit('setCartArticles',response.data);
                    console.log(response.data);
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    console.log("\n\n ----------------------\n\n");
                });
        },
        getCartRestaurantId(context, payload) {
             axios.get('rest/carts/getCartRestaurantID/')
                .then(response => {
                    console.log("\n\n -------Cart Id Restorana-------\n");
                    context.commit('setActiveCart',response.data);
                    console.log(response.data);
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    console.log("\n\n ----------------------\n\n");
                });
        },
        addToCart(context, payload) {
            let articleIdsWithQuantity = new Map();
            articleIdsWithQuantity = payload.map;
            axios.post('rest/carts/addArticlesToCart',
              articleIdsWithQuantity
          )
              .then(response => {
                  this.message = response.data;
                  console.log("\n\n -------Dodata korpa -------\n");
                  //console.log(response.data);
                  context.commit('setCartArticles', response.data);
                  context.commit('setActiveCart',payload.id);
                  console.log("\n\n ----------------------\n\n");
              })
              .catch(err => {
                  console.log("\n\n ------- ERROR -------\n");
                  console.log(err);
                  console.log("\n\n ----------------------\n\n");
              });
        },
        getCart(context, payload) {
            axios.get('rest/carts/' + payload.cartId)
                .then(response => {
                    this.message = response.data;
                    console.log("\n\n -------Kolica-------\n");
                    context.commit('setCart', response.data)
                    console.log(response.data);
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    console.log("\n\n ----------------------\n\n");
                });
        },
        changeCart(context,payload) {
            axios.post(
                'rest/carts/updateArticleQuantity/' + payload.id,
           payload.quantity, {
  headers: { "Content-type": "application/json" },
}
          )
              .then(response => {
                  this.message = response.data;
                  console.log("\n\n -------Dodata korpa -------\n");
                  console.log("prazni kart" + response.data);
                //   if (response.data === null) {
                //   context.commit('setActiveCart', -1);
                //   }
                  console.log("\n\n ----------------------\n\n");
              })
              .catch(err => {
                  console.log("\n\n ------- ERROR -------\n");
                  console.log(err);
                  console.log("\n\n ----------------------\n\n");
              });
        }
    },
    getters: {
       articles(state) {
            return state.articles;
        },
        cart(state) {
            return state.cart;
        },
        activeCart(state) {
            return state.activeCartId;
        },
    }
}
