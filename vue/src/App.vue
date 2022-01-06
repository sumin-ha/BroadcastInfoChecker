<template>
  <q-layout view="hHh lpR fFf">

    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg">
          </q-avatar>
          생방송/라이브 정보 수집기
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered>
      <q-list>
        <q-item-label header>메뉴</q-item-label>
        <Menu
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
import { ref } from 'vue'
import Menu from './components/Menu.vue'
import { mapGetters } from 'vuex'

const linksList = [
  {
    title: '알리미 추가 및 수정',
    caption: '추출 할 계정 정보 등록',
    icon: 'fas fa-info-circle',
    link: '1'
  },
  {
    title: '생방송 정보 취득',
    caption: '등록한 계정 정보 기준 트위터 탐색',
    icon: 'fab fa-twitter-square',
    link: '2'
  },
  {
    title: '등록 내용 확인',
    caption: '등록한 방송 정보를 확인, 수정, 삭제',
    icon: 'fas fa-book-reader',
    link: '3'
  },
  {
    title: '정보 수동 등록',
    caption: '다른 곳에서 공지 된 정보 등록',
    icon: 'fas fa-hand-paper',
    link: '4'
  }
];

export default {
  name: 'LayoutDefault',

  components: {
    Menu
  },

  computed: {
    ...mapGetters([
      'getPageIndex'
    ])
  },

  setup () {
    const leftDrawerOpen = ref(false)

    return {
      essentialLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  }
}
</script>