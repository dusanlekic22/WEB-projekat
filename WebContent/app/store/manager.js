var managerStore = {
    namespaced: true,
    state() {
        return {
            freeManagers: [],
            module: 'manager'
        };
    },
    mutations: {
        setFreeManagers(state, payload) {
            state.freeManagers = payload;
            console.log(state.freeManagers);
        },
    },
    actions: {
   getFreeManagers(context, payload) {
             axios
                .get('rest/manager')
                .then(response => {
                    console.log("\n\n ------- Slobodni menadzeri -------\n");
                    console.log(response.data);
                    context.state.freeManagers = response.data;
                    context.commit('setFreeManagers',response.data);
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
        module(state) {
            return state.module;
        },
        freeManagers(state) {
            return state.freeManagers;
        },
    }
}
