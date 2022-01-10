<template>
  <div>
    <div class="q-pa-md">
        <q-form
          @reset="onReset">
            <q-input
                v-model="account"
                label="계정명"
                hint="@을 제외한 트위터 계정명"
                style="width:200px" />
            <q-input
                v-model="keyword"
                label="검색 기준 단어"
                hint="구분은 쉼표(,) 3개까지 허용"
                style="width:300px" />
            <div class="q-pt-md">
                <q-btn @click="saveKeyword" label="등록" type="submit" color="primary"></q-btn>
                <q-btn label="지우기" type="reset" color="primary" flat class="q-ml-sm"></q-btn>
            </div>
        </q-form>
    </div>
    <div>
      <q-table
        title="등록 내용 확인"
        :rows="rows"
        :columns="columns"
        row-key="id"
        :selected-rows-label="getSelectedString"
        selection="multiple"
        v-model:selected="selected">
      </q-table>
      <q-btn class="q-mt-sm q-ml-sm" label="선택 삭제" type="submit" color="deep-orange" glossy></q-btn>
    </div>
    <div class="q-mt-md">
      Selected: {{ JSON.stringify(selected) }}
      checklist : {{ rows }}
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'

const columns = [
  {
    name: 'twitterAccount',
    required: true,
    label: '계정명',
    align: 'left',
    field: row => row.twitterAccount,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'searchKeyword',
    label: '검색 키워드',
    align: 'left',
    field: row => row.searchKeyword,
    sortable: false 
  }
]

export default {
  methods: {
    saveKeyword(list) {
      this.$store.dispatch('saveKeyWordList', list)
    },
    removeKeywords(list) {
      this.$store.dispatch('removeKeyWordList', list)
    }
  },
  setup () {
    const selected = ref([])
    const account = ref(null)
    const keyword = ref(null)

    const store = useStore();
    const rows = store.getters['getKeyWordList']

    return {
      account,
      keyword,
      selected,
      columns,
      rows,

      getSelectedString () {
        return selected.value.length === 0 ? '' : `${selected.value.length} record${selected.value.length > 1 ? 's' : ''} selected of ${rows.length}`
      },

      onReset () {
        account.value = null
        keyword.value = null

        account.value.resetValidation()
        keyword.value.resetValidation()
      }
    }
  }
}
</script>

<style>

</style>