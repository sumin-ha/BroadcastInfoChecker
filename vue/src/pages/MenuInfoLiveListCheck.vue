<template>
    <transition-group name="listList" tag="div">
        <span
            v-for="(data, index) in dataList"
            :key="data">
                <LiveInfoComp 
                    v-bind:dataOne="data"
                    v-bind:buttonFlag="2"
                    v-bind:index="index" />
        </span>
    </transition-group>
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
        const dataList = store.getters['getLiveList']

        return {
            dataList
        }
    }
}
</script>

<style>

</style>