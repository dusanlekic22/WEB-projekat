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
    actions: {},
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
