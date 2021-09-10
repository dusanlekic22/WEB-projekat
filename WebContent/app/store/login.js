var loginStore = {
    namespaced: true,
    state() {
        return {
            module: 'logovanje',
            loginActive: false,
            usernameError: false,
            passwordError: false,
            user: {}
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
                    this.user = response.data;
                    context.commit('userModule/setUser', this.user, { root: true });
                    console.log("\n\n ----------------------\n\n");
                })
                .catch(err => {
                    console.log("\n\n ------- ERROR -------\n");
                    console.log(err);
                    context.state.usernameError = true;
                    context.state.passwordError = true;
                    console.log("\n\n ----------------------\n\n");
                });
            // this.$store.commit('userModule/setUser', this.user);
            //console.log(context.mutations['userModule/setUser']);

        },
        logout(context, payload) {
            axios
                .post('rest/users/logout')
                .then(response => {
                    context.commit('userModule/loggedOut', this.user, { root: true });
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
        },
        getUsernameError(state) {
            return state.usernameError;
        },
        getPasswordError(state) {
            return state.passwordError;
        },
    }
}
