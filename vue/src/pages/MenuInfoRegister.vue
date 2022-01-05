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
                <q-btn label="등록" type="submit" color="primary"></q-btn>
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
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

const columns = [
  {
    name: 'account',
    required: true,
    label: '계정명',
    align: 'left',
    field: row => row.account,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'keyword',
    label: '검색 키워드',
    align: 'left',
    field: row => row.keyword,
    sortable: false }
]

// 더미 데이터
const rows = [
  {
    id: 1,
    account: 'LoveLive_Staff',
    keyword: '러브라이브'
  },
  {
    id: 2,
    account: 'SayuriDate',
    keyword: '다테사유리, 슈퍼스타'
  },
  {
    id: 3,
    account: 'Shuka_Saito',
    keyword: '사이토 슈카, 선샤인'
  },
  {
    id: 4,
    account: 'AoyamaNagisa',
    keyword: '나기사, 아오야마나기사, 슈퍼스타'
  },
  {
    id: 5,
    account: 'Rikako_Aida',
    keyword: '아이다상, 리캬코, 선샤인'
  },
]

export default {
  setup () {
    const selected = ref([])
    const account = ref(null)
    const keyword = ref(null)

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