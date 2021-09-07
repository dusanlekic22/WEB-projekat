var restaurantsStore = {
    namespaced: true,
    state() {
        return {
            restaurants: []
        };
    },
    mutations: {
        setRestaurants(state, payload) {
            state.restaurants = payload;
        },
    },
    actions: {
        getRestaurants(context, payload) {
            axios.get('rest/restaurants')
                .then(response => {
                    console.log("\n\n -------Restorani -------\n");
                    context.commit('setRestaurants',response.data);
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    console.log("\n\n ----------------------\n\n");
                });
        },
        filterByType(context, payload) {
            axios.get('rest/restaurants/filterByType')
                .then(response => {
                    console.log("\n\n -------Restorani -------\n");
                    context.commit('setRestaurants',response.data);
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
        restaurants(state) {
            return state.restaurants;
        }
    }
}