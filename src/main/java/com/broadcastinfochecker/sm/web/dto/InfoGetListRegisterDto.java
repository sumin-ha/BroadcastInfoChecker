package com.broadcastinfochecker.sm.web.dto;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 추출 후 정제한 데이터를 담아두는 DTO
 * */
@Getter
@NoArgsConstructor
public class InfoGetListRegisterDto {

    private String id;
    private String title;
    private String context;
    private String tag;
    private String broadcastDate;
    private String tweetAccount;
    private String source;

    // DB에 들어갈 Entity를 생성한다.
    public BroadcastInfo toEntity() {
        // String 형식의 Date를 LocalDateTime 형식으로
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(broadcastDate, formatter);
        return BroadcastInfo.builder()
                .title(title)
                .context(context)
                .tag(tag)
                .broadcastDate(date)
                .source(source)
                .tweetAccount(tweetAccount)
                .build();
    }

    @Builder
    public InfoGetListRegisterDto(String id, String broadcastTitle, String broadcastContext, String broadcastTag, LocalDateTime broadcastDate, String tweetAccount, String source) {
        this.id = id;
        this.title = broadcastTitle;
        this.context = broadcastContext;
        this.tag = broadcastTag;
        this.broadcastDate = broadcastDate.toString();
        this.tweetAccount = tweetAccount;
        this.source = source;
    }
}
