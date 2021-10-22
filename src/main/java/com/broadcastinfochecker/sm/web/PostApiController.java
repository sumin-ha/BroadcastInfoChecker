package com.broadcastinfochecker.sm.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PostApiController {

    @GetMapping("/api/")
    public String index() {
        // 화면에 돌려주는 모델에 리스트 데이터를 담음
//        model.addAttribute("posts", postsService.findAllDesc());
//        if(user != null) {
//            model.addAttribute("userName", user.getName());
//        }
        // 리턴값은 띄울 화면의 파일명 (확장자 제외)
        return "index";
    }
}
