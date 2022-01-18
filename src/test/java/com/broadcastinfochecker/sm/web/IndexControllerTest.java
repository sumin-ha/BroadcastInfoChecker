package com.broadcastinfochecker.sm.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    // index 페이지 로딩 테스트
//    @Test
//    public void testIndex01() {
//        // 테스트 실행
//        String body = this.restTemplate.getForObject("/", String.class);
//
//        // 결과 체크
//        assertThat(body).contains("생방송/라이브 정보 등록");
//    }
//
//    // menuInfoRegister 페이지 로딩 테스트
//    @Test
//    public void testMenuInfoRegister01() {
//        // 테스트 실행
//        String body = this.restTemplate.getForObject("/menuInfoRegister", String.class);
//
//        // 결과 체크
//        assertThat(body).contains("알리미 계정 정보와 검색 단어 등록 및 수정");
//    }
//
//    // menuInfoGetList 페이지 로딩 테스트
//    @Test
//    public void testMenuInfoGetList01() {
//        // 테스트 실행
//        String body = this.restTemplate.getForObject("/menuInfoGetList", String.class);
//
//        // 결과 체크
//        assertThat(body).contains("추출한 방송 정보 정제");
//    }
//
//    // menuInfoRegister 페이지 로딩 테스트
//    @Test
//    public void testMenuInfoCheck01() {
//        // 테스트 실행
//        String body = this.restTemplate.getForObject("/menuInfoCheck", String.class);
//
//        // 결과 체크
//        assertThat(body).contains("정제한 방송 정보 확인 및 작업");
//    }
//
//    // menuInfoRegister 페이지 로딩 테스트
//    @Test
//    public void testMenuInfoManualRegister01() {
//        // 테스트 실행
//        String body = this.restTemplate.getForObject("/menuInfoManualRegister", String.class);
//
//        // 결과 체크
//        assertThat(body).contains("방송 정보 수동 등록");
//    }
}
