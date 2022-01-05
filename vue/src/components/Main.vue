<template>
  <div>
      <div class="q-pa-md">
          test
      </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
setup () {
    const selected = ref([])
    const broadcastTitle = ref(null)
    const broadcastContext = ref(null)
    const broadcastTag = ref(null)
    const broadcastDate = ref(null)
    const source = ref(null)

    return {
      selected,
      broadcastTitle,
      broadcastContext,
      broadcastTag,
      broadcastDate,
      source,
    }
  }
}
</script>

<style>

</style>