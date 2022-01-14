<template>
    <div class="q-pt-md q-pl-md">
        <q-btn outline @click="reflashList" style="color: goldenrod;" label="새로 읽어오기" />
    </div>
    <transition-group name="listList" tag="div">
        <span
            v-for="(data,index) in dataList"
            :key="data">
                <LiveInfoComp 
                    v-bind:dataOne="data" 
                    v-bind:buttonFlag="1"
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
    methods: {
        reflashList() {
            this.$store.dispatch('twitterReaderReflash');
        }
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