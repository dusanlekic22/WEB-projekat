var cartStore = {
    namespaced: true,
    state() {
        return {
            articles: new Map(),
            cart:null
        };
    },
    mutations: {
        setCartArticles(state, payload) {
            state.articles = new Map(Object.entries(payload));
        },
        setCart(state, payload) {
            state.cart = payload;
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
    },
    getters: {
       articles(state) {
            return state.articles;
        },
        cart(state) {
            return state.cart;
        }
    }
}