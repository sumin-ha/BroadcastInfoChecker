package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.service.PostApiService;
import com.broadcastinfochecker.sm.web.dto.InfoGetListRegisterDto;
import com.broadcastinfochecker.sm.web.dto.InfoRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * post api 요청을 수신하는 컨트롤러
 */
@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostApiService postApiService;

    // 트위터 계정 & 키워드 등록용
    @PostMapping("api/account/register")
    public Long menuInfoRegister(@RequestBody InfoRegisterDto requestDto) {
        return postApiService.infoRegisterSave(requestDto);
    }

    // 트위터 계정 & 키워드 삭제용
    @PostMapping("api/account/remove")
    public void menuInfoRemove(@RequestBody List<InfoRegisterDto> requestDtoList) {
        postApiService.infoRegisterDelete(requestDtoList);
    }

    // 등록한 계정&키워드를 가지고 트위터 api와 연동, 원하는 정보를 습득하는 api
    @PostMapping("/api/get/info")
    public Long menuInfoGetList() {
        return postApiService.infoGetList();
    }

    // 정제 된 정보를 등록하는 api
    @PostMapping("/api/info/register")
    public Long menuInfoGetListRegister(@RequestBody InfoGetListRegisterDto requestDto) {
        postApiService.infoGetListRegister(requestDto);
        return 0L;
    }

    // 정제 된 정보를 수정하는 api
    @PostMapping("/api/info/update")
    public Long menuInfoCheckUpdate(@RequestBody List<InfoGetListRegisterDto> requestDtoList) {
        postApiService.infoCheckUpdate(requestDtoList);
        return 0L;
    }

    // 정제 된 정보를 삭제하는 api
    @PostMapping("/api/info/delete")
    public Long menuInfoCheckDelete(@RequestBody List<String> targetList) {
        postApiService.infoCheckDelete(targetList);
        return 0L;
    }
}

