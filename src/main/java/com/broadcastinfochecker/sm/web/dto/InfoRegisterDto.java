package com.broadcastinfochecker.sm.web.dto;

import com.broadcastinfochecker.sm.domain.posts.InfoRegister;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 긁어올 대상 트위터 계정명과 키워드를 저장
 * */
@Getter
@NoArgsConstructor
public class InfoRegisterDto {

    // 트위터 계정명 (@뒤의 영문자)
    private String twitterAccount;

    // 각 트윗에서 추출 할 방송의 추출 키워드
    // [,]를 기준으로 여러 키워드를 등록가능
    // 최대 갯수를 지정할 필요가 있을 듯 함.
    // ,를 split하지 않고, 그대로 DB에 삽입함
    private String keyword;

    // DB에 들어갈 Entity를 생성한다.
    public InfoRegister toEntity() {
        return InfoRegister.builder().twitterId(twitterAccount).keyword(keyword).build();
    }
}