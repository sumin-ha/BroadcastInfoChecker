// vuex setup
import axios from 'axios'

const menuInfoRegisterList = {
    fetch() {
        let arr = [];
        axios.get("api/menuInfoRegister")
            .then((res) => {
                for(const i in res.data) {
                    arr.push(res.data[i]);
                }
            })
            .catch((error) => {
            console.log(error);
            });
        return arr;
    }
}

const axiosConfig = {
    headers:{
        "Content-Type": "application/json"
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
        // 계정명, 키워드 저장
        pushKeyWordList: (state, list) => {
            console.log("save : " + list);
            state.keyWordList.push(list);
        },
        spliceKeyWordList: (state, obj) => {
            console.log(obj.index + " delete : " + obj.twitterAccount);
            state.keyWordList.splice(obj.index,1);
        },
    },
    actions: {
        // 계정명, 키워드 저장
        saveKeyWordList: (context, list) => {
            axios.post("api/account/register", list)
            .then(() => {
                context.commit('pushKeyWordList', list)
            })
            .catch((error) => {
            console.log(error);
            });
        },
        // 계정명, 키워드 삭제
        removeKeyWordList: (context, lists) => {
            console.log(lists);
            axios.post("api/account/remove", lists, axiosConfig)
            .then(() => {
                const arrObj = JSON.parse(lists);
                for(const i in arrObj) {
                    context.commit('spliceKeyWordList', arrObj[i])
                }
            })
            .catch((error) => {
            console.log(error);
            });
        },
    }
}

export default store