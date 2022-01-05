// vuex setup

const store = {
    state: {
        pageIndex: 9990
    },
    getters: {
        getPageIndex: (state) => {
            return state.pageIndex
        }
    },
    mutations: {
        setPageIndex: (state, index) => {
            state.pageIndex = index
        }
    },
    actions: {
        setPageIndex: (context, index) => {
            context.commit('setPageIndex', index)
        }
    }
}

export default store