<template>
    <div
      v-for="(data, index) in dataList"
      :key="data.broadcastTitle">
        <LiveInfoComp 
            v-bind:dataOne="data"
            v-bind:buttonFlag="2"
            v-bind:index="index" />
    </div>
</template>

<script>
import { useStore } from 'vuex'
import LiveInfoComp from '@/components/LiveInfoComp.vue'

// const dataList = [
//     {
//       broadcastTitle: '방송3',
//       broadcastContext: '방송3 본문',
//       broadcastTag: '태그7',
//       broadcastDate: '2022-01-03 12:00',
//       tweetAccount: 'bc3',
//       source: 'https://naver.com',
//     },
//     {
//       broadcastTitle: '방송4',
//       broadcastContext: '방송4 본문',
//       broadcastTag: '태그5, 태그6',
//       broadcastDate: '2022-01-04 12:00',
//       tweetAccount: 'bc4',
//       source: 'https://naver.com',
//     }
// ]

export default {
    components : {
        LiveInfoComp
    },
    setup() {
        const store = useStore();
        const dataList = store.getters['getLiveList']

        return {
            dataList
        }
    }
}
</script>

<style>

</style>