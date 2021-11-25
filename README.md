# 방송 정보 수집 어플리케이션

트위터에 올라오는 방송 정보를 보다 편하게 수집하고,<br />
관련 방송 정보를 모아서 확인 가능한 어플리케이션.

개발 환경
- Language&FrameWork : JAVA11 & SpringBoot2.4.13
- IDE : IntelliJ
- Test : Junit4
- Build : Gradle
- DB : PostgreSQL(AWS RDS)
- Server : AWS EC2
- 연결 된 트위터 : https://twitter.com/_namahousouinfo

***

### 21.11.17 1차 개발 완료<br />
구현 완료한 기능은 아래와 같습니다.
- 수집할 트위터 계정명과 키워드 저장
- 상기 저장한 데이터를 활용하여 방송 정보 수집
- 수집한 방송 정보를 적절한 형태로 가공하여 저장
- 가공 시, 방송의 태그를 수동 지정
- 주기적으로 트윗하여 모아둔 정보를 게시   
  매일 오후 12시, 3시, 6시에 오늘을 포함하여 3일분의 방송정보를 게시.

***

#### 1차 개발 화면 미리보기
```
메인화면
각 메뉴로 이동 할 수 있는 화면
``` 
<img src="/document/img/메인화면.png"></img>

```
수집할 트위터 명과 키워드를 저장, 확인, 수정 가능한 페이지
```
<img src="/document/img/알리미 추가 및 수정.png"></img>

```
등록한 정보를 이용해 트위터에서 원하는 트윗 수집하는 페이지
버튼을 누르면 수집, 알림 창이 뜬 후 수집한 정보 표시
표시 된 정보를 트위터에 업로드 하기 좋게 편집하여, 방송 정보를 등록함.

방송명(요약) : 트위터에 주기적으로 게시 할 때 표시 되는 문구
방송 정보 내용 : 상세한 방송 정보(140자 제한)
                이 내용은 추후 구현할 기능에 사용할 예정.
방송 정보 태그 : 등록한 정보들을 묶어 검색 할 때 사용할 태그. 
                이 내용은 추후 구현할 기능에 사용할 예정.
방송 정보 일시 : 해당 방송 시작 일시
출처 : 해당 정보의 출처
       이 페이지에서는 수집한 트위터의 주소가 자동으로 등록
```
<img src="/document/img/방송 정보 취득 전.png"></img>   
<img src="/document/img/방송 정보 취득.png"></img>

```
등록한 정보를 확인하고, 수정 및 삭제가 가능한 페이지
출처는 수정 불가능하며, 그 외의 부분은 전부 수정가능.
``` 
<img src="/document/img/등록 내용 확인.png"></img>

```
정보 수동 등록
트위터에서 고지되는 정보가 아닌, 다른 사이트의 정보를 등록하는 기능
등록 내용은 [트위터 정보 취득 후 등록] 하는 부분과 같으며,
출처 부분만 직접 입력하도록 되어있음.

방송명(요약) : 트위터에 주기적으로 게시 할 때 표시 되는 문구
방송 정보 내용 : 상세한 방송 정보(140자 제한)
                이 내용은 추후 구현할 기능에 사용할 예정.
방송 정보 태그 : 등록한 정보들을 묶어 검색 할 때 사용할 태그. 
                이 내용은 추후 구현할 기능에 사용할 예정.
방송 정보 일시 : 해당 방송 시작 일시
출처 : 해당 정보의 출처
```
<img src="/document/img/정보 수동 등록.png"></img>


***
### 2차 개발 계획.<br />
2차 개발 때 목표로 하고자 하는 기능은 아래와 같습니다.
- 다수의 권한 있는 사람이 직접 계정&키워드 등록 혹은 방송 정보를 등록 할 수 있도록 권한 설정
- 권한을 제어하기 위한 ADMIN 페이지
- 운영 트위터에 멘션을 넣으면, 그 멘션의 내용을 해석하여 그 사용자가 원하는 방송 정보를 알려주는 기능
***
###개발 시기 미정의 기능<br />
- 가공하여 저장 할 때, 구글 캘린더에도 저장하기 구현
***
### 이력
[Ver 2021.10.22](/document/README-211022.md)
