package com.broadcastinfochecker.sm.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 추출 후 정제한 데이터를 담아두는 DTO
 */
@Getter
@NoArgsConstructor
public class BroadcastInfoView {

    private String id;
    private String title;
    private String context;
    private String tag;
    private String broadcastDate;
    private String tweetAccount;
    private String source;

    @Builder
    public BroadcastInfoView(String id, String title, String context, String tag, LocalDateTime broadcastDate, String tweetAccount, String source) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.tag = tag;
        this.broadcastDate = localDateTimeToString(broadcastDate);
        this.tweetAccount = tweetAccount;
        this.source = source;
    }

    private String localDateTimeToString(LocalDateTime broadcastDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = broadcastDate.format(formatter);
        System.out.println(formattedDateTime);
        return formattedDateTime;
    }
}
