package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.service.ListLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
