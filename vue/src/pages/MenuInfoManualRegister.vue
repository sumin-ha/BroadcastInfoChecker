<template>
    <LiveInfoComp v-bind:dataOne="data" v-bind:buttonFlag="0"/>
</template>

<script>
import LiveInfoComp from '@/components/LiveInfoComp.vue'

export default {
    components : {
        LiveInfoComp
    },
    setup() {
        const data = {}
        return {
            data
        }
    }
}
</script>

<style>

</style>