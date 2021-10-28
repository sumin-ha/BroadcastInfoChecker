package com.broadcastinfochecker.sm.service;

import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegisterRepository;
import com.broadcastinfochecker.sm.web.dto.InfoRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostApiService {

    private final TweetInfoRegisterRepository infoRegisterRepository;

    // 추출 정보 등록
    @Transactional
    public Long infoRegisterSave(InfoRegisterDto requestDto) {
        return infoRegisterRepository.save(requestDto.toEntity()).getId();
    }

    // 추출 정보 삭제
    @Transactional
    public void infoRegisterDelete(Long id) {
        TweetInfoRegister infoRegister = infoRegisterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        infoRegisterRepository.delete(infoRegister);
    }
}
