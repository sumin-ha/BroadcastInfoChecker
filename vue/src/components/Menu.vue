<template>
    <q-item clickable tag="div"
        @click="menuRouter" >
        <q-item-section v-if="icon" avatar>
            <q-icon :name="icon" />
        </q-item-section>
        <q-item-section>
            <q-item-label>{{ title }}</q-item-label>
            <q-item-label caption>{{ caption }}</q-item-label>
        </q-item-section>
    </q-item>
</template>

<script>
import { defineComponent } from 'vue'
// import { commit } from 'vuex'

export default defineComponent({
  name: 'EssentialLink',
  props: {
    title: {
      type: String,
      required: true
    },

    caption: {
      type: String,
      default: ''
    },

    link: {
      type: String,
      default: '#'
    },

    icon: {
      type: String,
      default: ''
    }
  },
  methods: {
      // 메뉴 클릭시, 실행 옵션
      menuRouter() {        
        if(this.link == 1) {
          // 알리미 추가 및 수정 링크
          console.log("검색 계정 및 키워드 관리");
          this.$router.push('/menuInfoRegister')
        } else if(this.link == 2) {
          console.log("생방송 정보 취득");
          // 트위터에서 정보 습득
          this.axios.post("api/get/info")
                      .then((res) => {
                        if(res.data == 0){
                          alert('습득 내용이 존재하지 않습니다. 키워드를 확인해주세요.');
                          return;
                        } else {
                          this.$router.push('/menuInfoGetLiveList')
                        }                        
                      })
                      .catch((error) => {
                        console.log(error);
                      });          
        } else if(this.link == 3) {
          console.log("등록 내용 확인");
          this.$router.push('/menuInfoLiveListCheck')
        } else if(this.link == 4) {
          console.log("정보 수동 등록");
          this.$router.push('/menuInfoManualRegister')
        } else if(this.link == 'main') {
          this.$router.push('/')
        } else {
          console.log('error');
        }
      }
  }
})


</script>

<style>

</style>