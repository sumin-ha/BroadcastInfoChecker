<template>
    <div class="q-pa-md" :id="indexNum">
        <div class="row">
            <div class="col-xs-12 col-sm-5">
            <q-input
                v-model="title"
                label="방송명 (요약)"
                hint="최대한 간단하게. ex)아쿠아 생방송"
                :rules="[val => !!val || '방송명 (요약)은 필수 항목입니다.']"
                />
            </div>
            <div class="col-xs-12 col-sm-5">
            <q-input
                v-if="buttonFlag==1"
                v-model="bcSource"
                label="출처"
                hint="출처 트위터 링크"
                readonly
                />
            <q-input
                v-if="buttonFlag!=1"
                v-model="bcSource"
                label="출처"
                hint="http://로 시작하는 주소"
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
                <q-input 
                    filled
                    v-model="bcDate"
                    :rules="[val => !!val || '방송 일시는 필수 항목입니다.']">
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
                    autogrow
                    v-model="context"
                    label="방송 정보 내용"
                />
            </div>
            <div class="col-xs-3 col-sm-2 q-mt-lg">
            <q-btn push v-if="buttonFlag==1" color="secondary" label="정보 등록" @click="submitLiveInfo" />
            <q-btn push v-if="buttonFlag==2" color="secondary" label="정보 수정" @click="submitLiveInfo" />            
            </div>
            <div class="col-xs-3 col-sm-2 q-mt-lg">
            <q-btn v-if="buttonFlag==1" @click="removeTempInfo" push color="red" label="삭제" />
            <q-btn v-if="buttonFlag==2" @click="removeInfo" push color="red" label="정보 삭제" />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['dataOne', 'buttonFlag', 'index'],
    data () {
        return {
            id: this.dataOne.id,
            title: this.dataOne.title,
            context: this.dataOne.context,
            tag: this.dataOne.tag,
            tweetAccount: this.dataOne.tweetAccount,
            bcDate: this.dataOne.broadcastDate,
            bcSource: this.dataOne.source,
            flag: this.buttonFlag,
            indexNum: this.index,
        }
    },
    methods: {
        submitLiveInfo() {
            const liveObj = {
                id: this.id,
                title:this.title,
                context:this.context,
                tag:this.tag,
                broadcastDate:this.bcDate,
                tweetAccount:this.tweetAccount,
                source:this.bcSource
            }

            if(this.title == undefined || this.bcDate == undefined) {
                alert('항목을 전부 입력하세요.')
                return;
            }

            if(this.flag == 1) {
                this.$store.dispatch('saveLiveInfoList', liveObj);
            } else if(this.flag == 2) {
                this.$store.dispatch('updateLiveInfoList', liveObj);
            } else {
                alert('잘못 된 요청입니다.');
            }
            
        },
        removeTempInfo() {
            console.log(document.getElementById(this.indexNum));
            document.getElementById(this.indexNum).remove();
            this.$store.dispatch('removeLiveTempList');
        },
        removeInfo() {
            const obj = {
                id : this.id,
            }
            this.$store.dispatch('removeLiveList', obj);
        }
    }
}
</script>

<style>

</style>