package com.broadcastinfochecker.sm.domain.posts;

import com.broadcastinfochecker.sm.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 방송 정보 등록용 엔티티
 */
@NoArgsConstructor
@Entity
@Getter
public class BroadcastInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 방송 정보 제목 (요약)
    @Column(nullable = false)
    private String title;

    // 방송 정보 내용
    @Column(nullable = false)
    private String context;

    // 방송 정보 태그 (카테고리)
    @Column
    private String tag;

    // 방송 정보 일시
    @Column(nullable = false)
    private LocalDateTime broadcastDate;

    // 방송 정보 출처
    @Column(nullable = false)
    private String source;

    @Builder
    public BroadcastInfo(String title, String context, String tag, LocalDateTime broadcastDate, String source) {
        this.title = title;
        this.context = context;
        this.tag = tag;
        this.broadcastDate = broadcastDate;
        this.source = source;
    }
}
