<template>
    <div
      v-for="data in dataList"
      :key="data.broadcastTitle">
        <LiveInfoComp v-bind:dataOne="data" v-bind:buttonFlag="1"/>
    </div>
</template>

<script>
import LiveInfoComp from '@/components/LiveInfoComp.vue'

const dataList = [
    {
      broadcastTitle: '방송1',
      broadcastContext: '방송1 본문',
      broadcastTag: '태그1, 태그2',
      broadcastDate: '2022-01-03 12:00',
      source: 'https://naver.com',
    },
    {
      broadcastTitle: '방송2',
      broadcastContext: '방송2 본문',
      broadcastTag: '태그3, 태그4',
      broadcastDate: '2022-01-04 12:00',
      source: 'https://naver.com',
    }
]


export default {
    components : {
        LiveInfoComp
    },
    setup() {
        return {
            dataList
        }
    }
}
</script>

<style>

</style>