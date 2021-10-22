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
@Table(name="InfoRegister")
public class InfoRegister extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String twitterId;

    @Column(length = 500, nullable = false)
    private String keyword;

    @Builder
    public InfoRegister(String twitterId, String keyword) {
        this.twitterId = twitterId;
        this.keyword = keyword;
    }
}
