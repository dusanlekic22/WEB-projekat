var restaurantStore = {
    namespaced: true,
    state() {},
    mutations: {},
    actions: {
        addRestaurantUpdateManager(context, payload) {
          axios.post('rest/restaurants/addRestaurant/'+ payload.manager , {
                "name": payload.name,
                "type": payload.type,
                "location": payload.location,
                "logo": payload.logo,
                })
                    .then(response => {
                        this.message = response.data;
                        console.log("\n\n -------Izmenjeni PODACI -------\n");
                        context.dispatch('managerModule/getFreeManagers',{}, { root: true })
                        console.log("\n\n ----------------------\n\n");
                    })
                    .catch(err => {
                        console.log("\n\n ------- ERROR -------\n");
                        console.log(err);
                        console.log("\n\n ----------------------\n\n");
                    })
           
    }

    },
    getters: {}
}
