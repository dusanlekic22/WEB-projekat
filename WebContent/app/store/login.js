var loginStore = {
    namespaced: true,
    state() {
        return {
            module: 'logovanje',
            loginActive: false
        };
    },
    mutations: {
        login(state, payload) {
        },
        openLogin(state) {
            state.loginActive = true;
        },
        closeLogin(state) {
            state.loginActive = false;
        }
    },
    actions: {
        login(context, payload) {
                 axios
                .post('rest/users/login', { "username": '' + payload.loginUsername, "password": '' + payload.loginPassword })
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
                });
        }
    },
        getters: {
            module(state) {
                return state.module;
            },
            isLoginActive(state) {
                return state.loginActive;
            }
        }
}
