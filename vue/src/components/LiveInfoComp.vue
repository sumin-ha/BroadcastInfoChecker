<template>
  <div class="q-pa-md">
        <div class="row">
            <div class="col-xs-12 col-sm-5">
            <q-input
                v-model="title"
                label="방송명 (요약)"
                hint="최대한 간단하게. ex)아쿠아 생방송"
                />
            </div>
            <div class="col-xs-12 col-sm-5">
            <q-input
                v-model="context"
                label="방송 정보 내용"
                hint="@을 제외한 트위터 계정명"
                />
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-5">
            <q-input
                v-model="tag"
                label="방송 정보 태그 (카테고리)"
                hint="3개까지 기억됩니다. 여러개일 경우 , 로 구분해주세요."
                />
            </div>
            <div class="col-xs-12 col-sm-5">
            <div class="q-mt-md">
                <q-item-label class="q-field__label">방송 정보 일시</q-item-label>
                <q-input filled v-model="bcDate">
                <template v-slot:prepend>
                    <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                        <q-date v-model="bcDate" mask="YYYY-MM-DD HH:mm">
                        <div class="row items-center justify-end">
                            <q-btn v-close-popup label="Close" color="primary" flat />
                        </div>
                        </q-date>
                    </q-popup-proxy>
                    </q-icon>
                </template>

                <template v-slot:append>
                    <q-icon name="access_time" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                        <q-time v-model="bcDate" mask="YYYY-MM-DD HH:mm" format24h>
                        <div class="row items-center justify-end">
                            <q-btn v-close-popup label="Close" color="primary" flat />
                        </div>
                        </q-time>
                    </q-popup-proxy>
                    </q-icon>
                </template>
                </q-input>
            </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-5">
            <q-input
                v-model="bcSource"
                label="출처"
                hint="http://로 시작하는 주소"
                />
            </div>
            <div class="col-xs-3 col-sm-2 q-mt-lg">
            <q-btn push color="secondary" label="정보 등록" />            
            </div>
            <div class="col-xs-3 col-sm-2 q-mt-lg">
            <q-btn v-if="buttonFlag==1" push color="red" label="삭제" />
            <q-btn v-if="buttonFlag==2" push color="red" label="정보 삭제" />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['dataOne', 'buttonFlag'],
    data () {
        return {
        title: this.dataOne.broadcastTitle,
        context: this.dataOne.broadcastContext,
        tag: this.dataOne.broadcastTag,
        bcDate: this.dataOne.broadcastDate,
        bcSource: this.dataOne.source,
        flag: this.buttonFlag,
        }
    }
}
</script>

<style>

</style>