package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import com.broadcastinfochecker.sm.service.ListLoadService;
import com.broadcastinfochecker.sm.service.PostApiService;
import com.broadcastinfochecker.sm.service.TwitterLoadingService;
import com.broadcastinfochecker.sm.web.dto.InfoRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * post api 요청을 수신하는 컨트롤러
 */
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostApiService postApiService;
    private final TwitterLoadingService twitterLoadingService;

    // 트위터 계정 & 키워드 등록용
    @PostMapping("api/account/register")
    public Long menuInfoRegister(@RequestBody InfoRegisterDto requestDto) {
        return postApiService.infoRegisterSave(requestDto);
    }

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
