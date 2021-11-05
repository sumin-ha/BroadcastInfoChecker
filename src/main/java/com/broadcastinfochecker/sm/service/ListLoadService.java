package com.broadcastinfochecker.sm.service;

import com.broadcastinfochecker.sm.domain.posts.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 각종 리스트들을 불러오는 메서드들을 모아둔 서비스
 */
@RequiredArgsConstructor
@Service
public class ListLoadService {

    private final TweetInfoRegisterRepository infoRegisterRepository;
    private final BroadcastInfoRepository broadcastInfoRepository;
    private final BroadcastInfoTempRepository broadcastInfoTempRepository;

    // 습득 대상 정보 전부 불러오는 메서드
    public List<TweetInfoRegister> getInfoRegisterList() {
        return infoRegisterRepository.findAllDesc();
    }

    // 트위터에서 추출 한 임시 데이터를 전부 불러오는 메서드
    public List<BroadcastInfoTemp> getInfoListTemp() {
        return broadcastInfoTempRepository.findAll();
    }

    // 정제 된 방송 정보를 전부 불러오는 메서드
    public List<BroadcastInfo> getInfoList() {
        return broadcastInfoRepository.findAll();
    }

    // 정제 된 방송 정보를 전부 불러오는 메서드
    public List<BroadcastInfo> getNext3DaysInfoList(LocalDateTime startDate, LocalDateTime endDate) {
        return broadcastInfoRepository.findNext3DaysAsc(startDate, endDate);
    }
}
