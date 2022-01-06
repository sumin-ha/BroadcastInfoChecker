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
          console.log("알리미 추가 및 수정 링크");

          this.axios.get("api/menuInfoRegister")
                      .then((res) => {
                        console.log(res);
                        console.log(res.data);
                      })
                      .catch((error) => {
                        console.log(error);
                      });
        } else if(this.link == 2) {
          console.log(2);
        } else if(this.link == 3) {
          console.log(3);
        } else if(this.link == 4) {
          console.log(4);
        } else {
          console.log('error')
        }
      }
  }
})


</script>

<style>

</style>