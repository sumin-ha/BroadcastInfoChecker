// router main

const Main = () => import('@/components/Main.vue')
const Admin = () => import('@/components/Admin.vue')
const Info = () => import('@/components/Info.vue')
const Join = () => import('@/components/Join.vue')
const Member = () => import('@/components/Member.vue')

const MenuInfoRegister = () => import('@/pages/MenuInfoRegister.vue')
const MenuInfoGetLiveList = () => import('@/pages/MenuInfoGetLiveList.vue')
const MenuInfoLiveListCheck = () => import('@/pages/MenuInfoLiveListCheck.vue')
const MenuInfoManualRegister = () => import('@/pages/MenuInfoManualRegister.vue')


const routes = [
    {path: '/', component: Main},
    {path: '/menuInfoRegister', component: MenuInfoRegister},
    {path: '/menuInfoGetLiveList', component: MenuInfoGetLiveList},
    {path: '/menuInfoLiveListCheck', component: MenuInfoLiveListCheck},
    {path: '/menuInfoManualRegister', component: MenuInfoManualRegister},

    {path: '/admin', component: Admin},
    {path: '/info', component: Info},
    {path: '/join', component: Join},
    {path: '/member', component: Member},
]

export default routes