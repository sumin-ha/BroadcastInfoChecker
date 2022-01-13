package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfo;
import com.broadcastinfochecker.sm.service.ListLoadService;
import com.broadcastinfochecker.sm.web.dto.BroadcastInfoView;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * get api 요청을 수신하는 컨트롤러
 */
@RequiredArgsConstructor
@RestController
public class GetApiController {

    // 목록 화면 불러오기 용 서비스
    private final ListLoadService listLoadService;

    // 알리미 추가 및 수정 화면 요청
    @GetMapping("/api/menuInfoRegister")
    public Object menuInfoRegister() {
        return listLoadService.getInfoRegisterList();
    }

    // 방송 정보를 취득하고 구글 캘린더 등록, 트위터 알림용 등록을 위한 데이터 정제 화면 요청
    @GetMapping("/api/menuInfoGetList")
    public Object menuInfoGetList() {
        return listLoadService.getInfoListTemp();
    }

    // 트위터 알림용으로 정제한 내용을 확인하는 화면 요청
    @GetMapping("/api/menuInfoCheck")
    public Object menuInfoCheck() {
        List<BroadcastInfoView> broadcastInfoViewList = new ArrayList<>();

        for(BroadcastInfo broadcastInfo : listLoadService.getInfoList()) {
            BroadcastInfoView broadcastInfoView = BroadcastInfoView.builder()
                    .id(broadcastInfo.getId().toString())
                    .title(broadcastInfo.getTitle())
                    .context(broadcastInfo.getContext())
                    .tag(broadcastInfo.getTag())
                    .broadcastDate(broadcastInfo.getBroadcastDate())
                    .tweetAccount(broadcastInfo.getTweetAccount())
                    .source(broadcastInfo.getSource())
                    .build();
            broadcastInfoViewList.add(broadcastInfoView);
        }
        return broadcastInfoViewList;
    }
}
