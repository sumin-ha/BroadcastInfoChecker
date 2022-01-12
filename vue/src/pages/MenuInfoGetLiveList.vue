<template>
    <div
      v-for="(data,index) in dataList"
      :key="data.broadcastTitle">
        <LiveInfoComp 
            v-bind:dataOne="data" 
            v-bind:buttonFlag="1"
            v-bind:index="index" />
    </div>
</template>

<script>
import { useStore } from 'vuex'
import LiveInfoComp from '@/components/LiveInfoComp.vue'

export default {
    components : {
        LiveInfoComp
    },
    setup() {
        const store = useStore();
        const dataList = store.getters['getLiveTempList']

        return {
            dataList
        }
    }
}
</script>

<style>

</style>