package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.service.TwitterLoadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final TwitterLoadingService twitterLoadingService;

    @GetMapping("/api/tweet/test")
    public String index() {
        // 화면에 돌려주는 모델에 리스트 데이터를 담음
//        model.addAttribute("posts", postsService.findAllDesc());
//        if(user != null) {
//            model.addAttribute("userName", user.getName());
//        }

        // 테스트 트위터 로딩
        twitterLoadingService.TestTwitterLoading();

        // 리턴값은 띄울 화면의 파일명 (확장자 제외)
        return "../../../index";
    }
}
