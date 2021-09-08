var cartStore = {
    namespaced: true,
    state() {
        return {
            cartArticles: []
        };
     },
    mutations: {},
    actions: {
        addToCart(context, payload) {
            let articleIdsWithQuantity = new Map();
            articleIdsWithQuantity = payload;
            axios.post('rest/carts/addArticlesToCart',
              articleIdsWithQuantity
          )
              .then(response => {
                  this.message = response.data;
                  console.log("\n\n -------Dodata korpa -------\n");
                  console.log(response.data);
                  console.log("\n\n ----------------------\n\n");
              })
              .catch(err => {
                  console.log("\n\n ------- ERROR -------\n");
                  console.log(err);
                  console.log("\n\n ----------------------\n\n");
              });
        }

    },
    getters: {}
}
