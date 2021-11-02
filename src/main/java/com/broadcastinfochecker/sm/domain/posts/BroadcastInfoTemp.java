package com.broadcastinfochecker.sm.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 방송 정보 등록용 임시 엔티티
 * 키워드를 이용해 트위터 정보를 추출 한 후, 정제 전의 임시 데이터 저장용
 */
@NoArgsConstructor
@Entity
@Getter
public class BroadcastInfoTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 트위터 계정명
    @Column(nullable = false)
    public String tweetAccount;

    // 추출 내용
    @Column(length = 300, nullable = false)
    public String context;

    // 출처 (해당 트윗의 번호)
    @Column(nullable = false)
    public String source;

    @Builder
    public BroadcastInfoTemp(String tweetAccount, String context, String source) {
        this.tweetAccount = tweetAccount;
        this.context = context;
        this.source = source;
    }
}
