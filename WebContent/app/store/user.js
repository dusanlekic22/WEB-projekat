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
                role: null,
                restaurantId: null,
            },
            logged: false,
            users: [],
            module: 'korisnik'
        };
    },
    mutations: {
        setUser(state, payload) {
            state.user = payload;
            state.logged = true;
            console.log(state.user);
        },
        loggedOut(state, payload){
            state.user = {
                username: '',
                password: '',
                name: '',
                surname: '',
                gender: '',
                dateOfBirth: '',
                role: null,
                restaurantId: null,
            };
            state.logged = false,
            users= [],
            module = 'korisnik'
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
        },
        editUser(context, payload) {
          axios.post('rest/edit/saveUserChanges', {
                "username": payload.username,
                "password": payload.password,
                "name": payload.name,
                "surname": payload.surname,
                "gender": payload.gender,
                "dateOfBirth": payload.dateOfBirth,
                "role": payload.role
                    })
                    .then(response => {
                        this.message = response.data;
                        console.log("\n\n -------Izmenjeni PODACI -------\n");
                        console.log(response.data);
                        context.state.user = response.data;
                        context.commit('setUser', context.state.user);
                        console.log("\n\n ----------------------\n\n");
                    })
                    .catch(err => {
                        console.log("\n\n ------- ERROR -------\n");
                        console.log(err);
                        console.log("\n\n ----------------------\n\n");
                    })
           
        },
        getUsers(context, payload) {
             axios
                .get('rest/users')
                .then(response => {
                    console.log("\n\n ------- Korisnici -------\n");
                    console.log(response.data);
                    context.state.users = response.data;
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
        },
        isManager(state) {
            if (state.user.role === 'MANAGER') {
                return true;
            }
        },
        isCustomer(state) {
            if (state.user.role === 'CUSTOMER') {
                return true;
            }
        },
        isDelivery(state) {
            if (state.user.role === 'DELIVERY') {
                return true;
            }
        },
        canSeeOrders(state) {
            if (state.user.role === 'DELIVERY' || state.user.role === 'CUSTOMER' || state.user.role === 'MANAGER') {
                return true;
            }
        },
        getterUsers(state) {
            return state.users;
        }
    }
}
