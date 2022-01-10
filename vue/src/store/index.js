// vuex setup

const menuInfoRegisterList = {
    fetch() {
        let arr = [];
        this.axios.get("api/menuInfoRegister")
        .then((res) => {
            arr = res.data;
        })
        .catch((error) => {
          console.log(error);
        });

        return arr;
    }
}

const store = {
    state: {
        keyWordList: menuInfoRegisterList.fetch(),
    },
    getters: {
        getKeyWordList: (state) => {
            return state.keyWordList
        },
    },
    mutations: {
        setKeyWordList: (state, list) => {
            state.keyWordList = list
        },
        saveKeyWordList: (state, list) => {
            console.log("save : " + list);
            state.keyWordList.push(list);
        },
        removeKeyWordList: (state, obj) => {
            console.log(obj.index + " delete : " + obj.twitterAccount);
            // localStorage.removeItem(obj.todoItem);
            state.keyWordList.splice(obj.index,1);
        },
    },
    actions: {
        setKeyWordList: (context, list) => {
            context.commit('setKeyWordList', list)
        },
        saveKeyWordList: (context, list) => {
            context.commit('saveKeyWordList', list)
        },
        removeKeyWordList: (context, lists) => {
            for(const list of lists) {
                context.commit('removeKeyWordList', list)
            }
        },
    }
}

export default store