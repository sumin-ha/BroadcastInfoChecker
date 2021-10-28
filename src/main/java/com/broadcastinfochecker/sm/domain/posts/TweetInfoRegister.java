package com.broadcastinfochecker.sm.domain.posts;

import com.broadcastinfochecker.sm.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 추출 대상(트위터)의 정보와 검색 대상 키워드를 저장하는 엔티티
 */
@NoArgsConstructor
@Entity
@Getter
public class TweetInfoRegister extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String twitterAccount;

    @Column(length = 100, nullable = false)
    private String searchKeyword;

    @Builder
    public TweetInfoRegister(String twitterAccount, String searchKeyword) {
        this.twitterAccount = twitterAccount;
        this.searchKeyword = searchKeyword;
    }
}
