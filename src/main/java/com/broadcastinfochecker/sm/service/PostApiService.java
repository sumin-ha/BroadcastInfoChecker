package com.broadcastinfochecker.sm.service;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfo;
import com.broadcastinfochecker.sm.domain.posts.BroadcastInfoRepository;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegisterRepository;
import com.broadcastinfochecker.sm.web.dto.InfoGetListRegisterDto;
import com.broadcastinfochecker.sm.web.dto.InfoRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * post 처리를 담당하는 서비스
 */
@RequiredArgsConstructor
@Service
public class PostApiService {

    private final TwitterService twitterLoadingService;
    private final BroadcastInfoRepository broadcastInfoRepository;
    private final TweetInfoRegisterRepository infoRegisterRepository;

    // 추출 정보 등록
    @Transactional
    public Long infoRegisterSave(InfoRegisterDto requestDto) {
        List<TweetInfoRegister> infoRegisterDtoList =
                infoRegisterRepository.findAll();
        for(TweetInfoRegister dto : infoRegisterDtoList) {
            // 이미 등록 된 계정 정보라면 해당 정보를 삭제함.
            if(dto.getTwitterAccount().equals(requestDto.getTwitterAccount())) {
                infoRegisterRepository.delete(dto);
                if(requestDto.getSearchKeyword().isEmpty()) {
                    return 0L;
                }
            }
        }
        // 등록
        return infoRegisterRepository.save(requestDto.toEntity()).getId();
    }

    // 추출 정보 삭제
    @Transactional
    public void infoRegisterDelete(List<InfoRegisterDto> list) {
        List<TweetInfoRegister> infoRegisterDtoList =
                infoRegisterRepository.findAll();

        List<TweetInfoRegister> removeTargetList = new ArrayList<>();

        // 삭제 할 대상 확인
        for(TweetInfoRegister dto : infoRegisterDtoList) {
            for(InfoRegisterDto requestDto : list) {
                if(dto.getTwitterAccount().equals(requestDto.getTwitterAccount())) {
                    removeTargetList.add(dto);

                    TweetInfoRegister infoRegister = infoRegisterRepository.findById(dto.getId())
                            .orElseThrow(() -> new IllegalArgumentException("해당 정보가 존재하지 않습니다. account=" + dto.getTwitterAccount()));
                }
            }
        }
        infoRegisterRepository.deleteAll(removeTargetList);
    }

    // 등록 된 계정 정보를 이용, 트위터 데이터 추출하는 메서드
    @Transactional
    public Long infoGetList() {
        // 등록 된 계정 정보를 불러옴.
        List<TweetInfoRegister> infoRegisterDtoList =
                infoRegisterRepository.findAll();
        // 추출 작업 시작
        return twitterLoadingService.infoGetList(infoRegisterDtoList);
    }

    // 정제한 방송 정보를 DB에 등록
    @Transactional
    public void infoGetListRegister(List<InfoGetListRegisterDto> requestDtoList) {
        List<BroadcastInfo> broadcastInfoList = new ArrayList<>();
        for(InfoGetListRegisterDto dto : requestDtoList) {
            broadcastInfoList.add(dto.toEntity());
        }
        // 등록
        broadcastInfoRepository.saveAll(broadcastInfoList);
    }

    // 정제한 방송 정보를 수정
    @Transactional
    public void infoCheckUpdate(List<InfoGetListRegisterDto> requestDtoList) {

        List<BroadcastInfo> updateTargetList = new ArrayList<>();
        List<BroadcastInfo> deleteTargetList = new ArrayList<>();
        List<BroadcastInfo> broadcastInfoList =
                broadcastInfoRepository.findAll();

        // 수정 대상 내용 삭제
        for(BroadcastInfo dto : broadcastInfoList) {
            // 이미 등록 된 방송 정보라면 해당 정보를 삭제함.
            for(InfoGetListRegisterDto requestDto : requestDtoList) {
                if(dto.getId().equals(Long.parseLong(requestDto.getId()))) {
                    deleteTargetList.add(dto);
                }
            }
        }
        broadcastInfoRepository.deleteAll(deleteTargetList);

        // 수정 대상 내용 등록
        for(InfoGetListRegisterDto dto : requestDtoList) {
            updateTargetList.add(dto.toEntity());
        }
        // 등록
        broadcastInfoRepository.saveAll(updateTargetList);
    }

    // 정제한 방송 정보를 삭제
    @Transactional
    public void infoCheckDelete(List<String> targetList) {
        List<BroadcastInfo> deleteTargetList = new ArrayList<>();
        List<BroadcastInfo> broadcastInfoList =
                broadcastInfoRepository.findAll();

        // 수정 대상 내용 삭제
        for(BroadcastInfo dto : broadcastInfoList) {
            // 이미 등록 된 방송 정보라면 해당 정보를 삭제함.
            for(String id : targetList) {
                if(dto.getId().equals(Long.parseLong(id))) {
                    deleteTargetList.add(dto);
                }
            }
        }
        broadcastInfoRepository.deleteAll(deleteTargetList);
    }
}
