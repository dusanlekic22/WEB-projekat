var ordersStore = {
    namespaced: true,
    state() {
        return {
            orders: [],
        };
    },
    mutations: {
        setOrders(state, payload) {
            state.orders = payload;
        },
        setProcessingOrders(state, payload) {
            state.processingOrders = payload;
        }
    },
    actions: {
        getOrders(context, payload) {
            axios.get('rest/orders')
                .then(response => {
                    console.log("\n\n -------Porudzbine -------\n");
                    context.commit('setOrders', response.data);
                    console.log(response.data);
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    console.log("\n\n ----------------------\n\n");
                });
        },
        getProcessingOrders(context,payload) {
            axios.put('rest/orders/filterByStatus/'+payload.status)
                .then(response => {
                    console.log("\n\n -------Porudzbine -------\n");
                    context.commit('setProcessingOrders', response.data);
                    console.log(response.data);
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    console.log("\n\n ----------------------\n\n");
                });
        },
        deleteOrder(context, payload) {
            axios.put('rest/orders/deleteOrder'+payload.orderId)
                .then(response => {
                    console.log("\n\n -------Porudzbine -------\n");
                    context.commit('setOrders', response.data);
                    console.log(response.data);
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    console.log("\n\n ----------------------\n\n");
                });
        },
        addOrder(context, payload) {
              axios.post('rest/orders/createOrder/' + payload.cartId,
              payload.cartPrice, {
  headers: { "Content-type": "application/json" }
}
              )
                .then(response => {
                    console.log("\n\n -------Porudzbine -------\n");
                    context.commit('setOrders', response.data);
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
    getters: {
        orders(state) {
            return state.orders;
        }
    }
}