package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.service.ListLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 화면 컨트롤러
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    // 목록 화면 불러오기 용 서비스
    private final ListLoadService listLoadService;

    // 접속 메인 페이지
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 알리미 추가 및 수정 화면 요청
    @GetMapping("/menuInfoRegister")
    public String menuInfoRegister(Model model) {
        // 화면에 돌려주는 모델에 리스트 데이터를 담음
        model.addAttribute("posts", listLoadService.getInfoRegisterList());
        return "menuInfoRegister";
    }

    // 방송 정보를 취득하고 구글 캘린더 등록, 트위터 알림용 등록을 위한 데이터 정제 화면 요청
    @GetMapping("/menuInfoGetList")
    public String menuInfoGetList(Model model) {
        // 화면에 돌려주는 모델에 리스트 데이터를 담음
        model.addAttribute("posts", listLoadService.getInfoListTemp());
        return "menuInfoGetList";
    }

    // 트위터 알림용으로 정제한 내용을 확인하는 화면 요청
    @GetMapping("/menuInfoCheck")
    public String menuInfoCheck(Model model) {
        // 화면에 돌려주는 모델에 리스트 데이터를 담음
        model.addAttribute("posts", listLoadService.getInfoList());
        return "menuInfoCheck";
    }

    // 정제한 내용을 수동으로 등록하는 화면 요청
    @GetMapping("/menuInfoManualRegister")
    public String menuInfoManualRegister(Model model) {
        return "menuInfoManualRegister";
    }

}
