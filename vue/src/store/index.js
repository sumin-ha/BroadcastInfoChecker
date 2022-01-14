// vuex setup
import axios from 'axios'

// post시 "application/json" 형식으로 보내기 위한 헤더 지정
const axiosConfig = {
    headers:{
        "Content-Type": "application/json"
    }
}

// 검색 계정 및 키워드 리스트 습득
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

// 생방송 정보 취득 요청
const menuInfoGetLiveList = {
    fetch() {
        let arr = [];
        axios.get("api/menuInfoGetList")
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

// 등록 내용 확인 취득 요청
const menuInfoLiveListCheck = {
    fetch() {
        let arr = [];
        axios.get("api/menuInfoCheck")
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


const store = {
    state: {
        keyWordList: menuInfoRegisterList.fetch(),
        liveTempList: menuInfoGetLiveList.fetch(),
        liveList: menuInfoLiveListCheck.fetch(),
    },
    getters: {
        getKeyWordList: (state) => {
            return state.keyWordList
        },
        getLiveTempList: (state) => {
            return state.liveTempList
        },
        getLiveList: (state) => {
            return state.liveList
        },
    },
    mutations: {
        // 검색 계정 및 키워드 저장
        pushKeyWordList: (state, list) => {
            console.log("save : " + list);
            state.keyWordList.push(list);
        },
        // 검색 계정 및 키워드 삭제
        spliceKeyWordList: (state, obj) => {
            console.log("delete : " + obj.twitterAccount);
            for(let i=0; i<state.keyWordList.length; i++) {
                const k = state.keyWordList[i];
                if(k.twitterAccount == obj.twitterAccount) {
                    state.keyWordList.splice(i,1);
                }
            }
        },
        // 방송 정보 취득 (수동) 등록 -> 등록 내용 확인 리스트 삽입
        saveLiveCheckList: (state, liveObj) => {
            state.liveList.push(liveObj);
        },
        // 방송 정보 취득 삭제
        spliceLiveTempList: (state, index) => {
            console.log("delete temp list index : " + index);
            state.liveTempList.splice(index,1);
        },
        // 등록 내용 삭제
        spliceLiveList: (state, id) => {
            for(let i=0; i<state.liveList.length; i++) {
                const k = state.liveList[i];
                if(k.id == id) {
                    state.liveList.splice(i,1);
                }
            }
        },
    },
    actions: {
        // 검색 계정 및 키워드 저장
        saveKeyWordList: (context, list) => {
            axios.post("api/account/register", list)
            .then(() => {
                context.commit('pushKeyWordList', list)
                alert('등록 완료.');
            })
            .catch((error) => {
            console.log(error);
            });
        },
        // 검색 계정 및 키워드 삭제
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
        // 습득 후 정제한 생방송 정보 등록(+확인 리스트 추가)
        saveLiveInfoList: (context, liveObj) => {
            axios.post("api/info/register", liveObj)
            .then(() => {
                console.log('ok');
                context.commit('saveLiveCheckList', liveObj)
                alert('등록 완료.');
            })
            .catch((error) => {
                console.log(error);
            });
        },
        // 습득 후 필요 없는 정보 삭제(vuex리스트에서 삭제)
        removeLiveTempList: (context, index) => {
            context.commit('spliceLiveTempList', index)
        },
        // 등록 내용 수정
        updateLiveInfoList: (context, liveObj) => {
            // 수정 api 부르기
            axios.post("api/info/update", liveObj)
            .then(() => {
                console.log('ok');
                alert('수정 완료.');                
            })
            .catch((error) => {
                console.log(error);
            });
        },
        // 등록 내용 삭제
        removeLiveList: (context, obj) => {
            // 삭제 api 부르기
            axios.post("api/info/delete", obj.id, axiosConfig)
            .then(() => {
                console.log('ok');
                context.commit('spliceLiveList', obj.id)
                alert('삭제 완료.');                
            })
            .catch((error) => {
                console.log(error);
            });
        },
    }
}

export default store