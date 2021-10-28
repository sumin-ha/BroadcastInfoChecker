package com.broadcastinfochecker.sm.service;

import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 각종 리스트들을 불러오는 메서드들을 모아둔 서비스
 */
@RequiredArgsConstructor
@Service
public class ListLoadService {

    private final TweetInfoRegisterRepository infoRegisterRepository;

    // 습득 대상 정보 전부 불러오는 메서드
    public List<TweetInfoRegister> getInfoRegisterList() {
        return infoRegisterRepository.findAllDesc();
    }
}
