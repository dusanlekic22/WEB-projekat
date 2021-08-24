var registrationStore = {
    namespaced: true,
    state() {
        return {
          module: 'registracija'
        };
    },
    mutations: {
        register(state, payload) {
            state.module;
        }
    },
    actions: {
        register(context, payload) {
            console.log(payload);
           
            axios.post('rest/users/registration', {
                        "username": payload.username,
                        "password": payload.password,
                        "role": "User"
                    })
                    .then(response => {
                        this.message = response.data;
                        console.log("\n\n ------- PODACI -------\n");
                        console.log(response.data);
                        console.log("\n\n ----------------------\n\n");
                    })
                    .catch(err => {
                        console.log("\n\n ------- ERROR -------\n");
                        console.log(err);
                        console.log("\n\n ----------------------\n\n");
                    })
        }
        },
    getters: {
            module(state) {
                return state.module;
            }
        }
    }
