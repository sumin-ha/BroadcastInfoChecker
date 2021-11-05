package com.broadcastinfochecker.sm.service;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 트위터 자동갱신 용 서비스
 */
@Service
@NoArgsConstructor
public class ScheduledService {

    /** 트위터 서비스 */
    @Autowired
    private TwitterService twitterService;
    /** 리스트 불러오기 서비스 */
    @Autowired
    private ListLoadService listLoadService;

//    @Scheduled(fixedDelay = 10000)
//    public void testJob() {
//        System.out.println("test scheduled - " + System.currentTimeMillis() / 1000);
//
//        twitterService.twitterPost("");
//    }

    // 오늘. 내일. 모래. 3일치의 방송 정보를 정제하여 트위터 업로드 하는 스케쥴.
    // 시간은 매일 정오, 15시, 18시 3회 예정.
    @Scheduled(cron = "0 0 12,15,18 * * *")
    public void titleInfoAlertJobNext3Days() {

        // 오늘 데이터 습득
        LocalDateTime today = LocalDateTime.now();
        LocalDate todayLocalDate = today.toLocalDate();
        // 3일 후의 23시 59분 59초를 습득
        LocalDateTime next3dayTemp = today.plusDays(2);
        LocalDateTime next3day = LocalDateTime.of(next3dayTemp.getYear(),
                next3dayTemp.getMonthValue(),
                next3dayTemp.getDayOfMonth(),
                23, 59,59);

        // 향후 3일의 데이터를 습득
        List<BroadcastInfo> broadcastInfoList =
                listLoadService.getNext3DaysInfoList(today, next3day);

        // 트윗 단위로 저장 될 리스트
        List<String> tweetList = new ArrayList<>();
        // 일자 별로 가공
        StringBuilder stringBuilder = new StringBuilder(); // 만든 트윗 저장용
        stringBuilder.append(today.getMonthValue() + "/" + today.getDayOfMonth() + " 방송 정보");
        stringBuilder.append("\n");
        stringBuilder.append("\n"); // 오늘 날짜 방송정보 초기 설정

        int dayCount = -1; // 오늘, 내일, 글피 구분용
        boolean dayFlag = true; // 트윗 서두의 날짜 붙이기용 플래그
        for(BroadcastInfo broadcastInfo : broadcastInfoList) {

            // 트윗 서두의 날짜 붙이기
            if(todayLocalDate.plusDays(dayCount).isBefore(broadcastInfo.getBroadcastDate().toLocalDate())) {
                // 오늘이 아닌, 내일 모래일 경우엔 스트링 빌더에 값이 존재하므로 그 값을 비워준다.
                if (!todayLocalDate.isEqual(broadcastInfo.getBroadcastDate().toLocalDate())) {
                    tweetList.add(stringBuilder.toString());
                }
                dayCount++;
                stringBuilder = new StringBuilder();
                stringBuilder.append(broadcastInfo.getBroadcastDate().getMonthValue() + "/" + broadcastInfo.getBroadcastDate().getDayOfMonth() + " 방송 정보");
                stringBuilder.append("\n");
                stringBuilder.append("\n");
            }
            String str = broadcastInfo.getBroadcastDate().toLocalTime().toString() + " " + broadcastInfo.getTitle();

            // 넣기 전, 스트링 빌더의 길이와 만든 문장의 길이가 140을 넘긴다면, 기존 스트링빌더는 트윗 1개로서 리스트에 삽입 후 초기화한다.
            if(stringBuilder.toString().length() + str.length() + 2 < 140) {
                stringBuilder.append(str);
                stringBuilder.append("\n");
            } else if(stringBuilder.toString().length() + str.length() < 140) {
                stringBuilder.append(str);
            } else {
                // 글자수 제한 초과시, 스트링 빌더를 초기화 한 후 삽입.
                tweetList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append(broadcastInfo.getBroadcastDate().getMonthValue() + "/" + broadcastInfo.getBroadcastDate().getDayOfMonth() + " 방송 정보");
                stringBuilder.append("\n");
                stringBuilder.append("\n");
                stringBuilder.append(str);
                stringBuilder.append("\n");
            }
        }
        // 제일 마지막으로 만들어 진 트윗도 리스트에 포함.
        tweetList.add(stringBuilder.toString());

        // 만들어진 각 트윗을 포스트
        for(String str : tweetList){
            twitterService.twitterPost(str);
        }
    }
}


