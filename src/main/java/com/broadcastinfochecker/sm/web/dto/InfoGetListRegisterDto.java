package com.broadcastinfochecker.sm.web.dto;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 추출 후 정제한 데이터를 담아두는 DTO
 * */
@Getter
@NoArgsConstructor
public class InfoGetListRegisterDto {

    private String id;
    private String broadcastTitle;
    private String broadcastContext;
    private String broadcastTag;
    private LocalDateTime broadcastDate;
    private String tweetAccount;
    private String source;

    // DB에 들어갈 Entity를 생성한다.
    public BroadcastInfo toEntity() {
        return BroadcastInfo.builder()
                .title(broadcastTitle)
                .context(broadcastContext)
                .tag(broadcastTag)
                .broadcastDate(broadcastDate)
                .source(source)
                .tweetAccount(tweetAccount)
                .build();
    }

    @Builder
    public InfoGetListRegisterDto(String id, String broadcastTitle, String broadcastContext, String broadcastTag, LocalDateTime broadcastDate, String tweetAccount, String source) {
        this.id = id;
        this.broadcastTitle = broadcastTitle;
        this.broadcastContext = broadcastContext;
        this.broadcastTag = broadcastTag;
        this.broadcastDate = broadcastDate;
        this.tweetAccount = tweetAccount;
        this.source = source;
    }
}
