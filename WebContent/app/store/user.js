var userStore = {
    namespaced: true,
    state() {
        return {
            user: {
                username: '',
                password: '',
                name: '',
                surname: '',
                gender: '',
                dateOfBirth: '',
                role: null
            },
            logged: false,
            module: 'korisnik'
        };
    },
    mutations: {
        setUser(state, payload) {
            state.user = payload;
            state.logged = true;
            console.log(state.user);
        },
    },
    actions: {
   setCurrentUser(context, payload) {
             axios
                .get('rest/edit/profileUser')
                .then(response => {
                    console.log("\n\n ------- Ulogovani -------\n");
                    console.log(response.data);
                    context.state.user = response.data;
                    context.commit('setUser', context.state.user);
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
        user(state) {
            return state.user;
        },
        logged(state) {
            return state.logged;
        },
        isAdmin(state) {
            if (state.user.role === 'ADMINISTRATOR') {
                return true;
            }
        }
    }
}
