package com.broadcastinfochecker.sm.service;

import com.broadcastinfochecker.sm.domain.posts.InfoRegister;
import com.broadcastinfochecker.sm.domain.posts.InfoRegisterRepository;
import com.broadcastinfochecker.sm.web.dto.InfoRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostApiService {

    private InfoRegisterRepository infoRegisterRepository;

    // 추출 정보 등록
    @Transactional
    public Long infoRegisterSave(InfoRegisterDto requestDto) {
        return infoRegisterRepository.save(requestDto.toEntity()).getId();
    }

    // 추출 정보 삭제
    @Transactional
    public void infoRegisterDelete(Long id) {
        InfoRegister infoRegister = infoRegisterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        infoRegisterRepository.delete(infoRegister);
    }
}
