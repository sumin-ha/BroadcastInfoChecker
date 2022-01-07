// vuex setup

const store = {
    state: {
        pageIndex: 9990,
        keyWordList: [],
    },
    getters: {
        getPageIndex: (state) => {
            return state.pageIndex
        },
        getKeyWordList: (state) => {
            return state.keyWordList
        },
    },
    mutations: {
        setPageIndex: (state, index) => {
            state.pageIndex = index
        },
        setKeyWordList: (state, list) => {
            state.keyWordList = list
        }
    },
    actions: {
        setPageIndex: (context, index) => {
            context.commit('setPageIndex', index)
        },
        setKeyWordList: (context, list) => {
            context.commit('setKeyWordList', list)
        }
    }
}

export default store