package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfo;
import com.broadcastinfochecker.sm.domain.posts.BroadcastInfoRepository;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegisterRepository;
import com.broadcastinfochecker.sm.service.TwitterService;
import com.broadcastinfochecker.sm.web.dto.InfoGetListRegisterDto;
import com.broadcastinfochecker.sm.web.dto.InfoRegisterDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    private MockMvc mvc;

    @MockBean
    private TwitterService twitterLoadingService;

    @Autowired
    private TweetInfoRegisterRepository infoRegisterRepository;

    @Autowired
    private BroadcastInfoRepository broadcastInfoRepository;

//    @After
//    public void cleanUp() {
//        infoRegisterRepository.deleteAll();
//        broadcastInfoRepository.deleteAll();
//    }
//
    @Autowired
    private WebApplicationContext context;

    @Autowired ObjectMapper objectMapper;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    // menuInfoRegister 테스트
    /**
     * 정상 테스트
     *
     * 입력값 트위터 계정명과 일치하는 데이터가 DB에 존재하지 않음, 신규 등록.
     * 저장에 성공하여 데이터의 id를 리턴받음.
     *
     * @throws Exception
     */
    @Test
    public void testMenuInfoRegister01() throws Exception {
        // 조건 설정
        String twitterAccount = "testAccount";
        String keyword = "testKeyword";
        InfoRegisterDto requestDto = InfoRegisterDto.builder()
                .twitterAccount(twitterAccount)
                .searchKeyword(keyword)
                .build();

        String url = "http://localhost:" + port + "/api/account/register";

        // 테스트 실행
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // 결과 비교
        List<TweetInfoRegister> all = infoRegisterRepository.findAll();
        assertThat(all.get(0).getTwitterAccount()).isEqualTo(twitterAccount);
        assertThat(all.get(0).getSearchKeyword()).isEqualTo(keyword);
    }

    /**
     * 정상 테스트
     *
     * 입력값 트위터 계정명과 일치하는 데이터가 DB에 존재함, 수정 등록 (삭제 후 재등록)
     * 저장에 성공하여 데이터의 id를 리턴받음.
     *
     * @throws Exception
     */
    @Test
    public void testMenuInfoRegister02() throws Exception {
        // 조건 설정
        String twitterAccount = "testAccount";
        String keyword = "testKeyword";
        InfoRegisterDto requestDto = InfoRegisterDto.builder()
                .twitterAccount(twitterAccount)
                .searchKeyword(keyword)
                .build();

        // DB 선 데이터 입력
        TweetInfoRegister tempTweetInfoRegister =
                TweetInfoRegister.builder()
                        .twitterAccount(twitterAccount)
                        .searchKeyword("testOldKeyword")
                        .build();
        infoRegisterRepository.save(tempTweetInfoRegister);

        String url = "http://localhost:" + port + "/api/account/register";

        // 테스트 실행
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // 결과 비교
        List<TweetInfoRegister> all = infoRegisterRepository.findAll();
        assertThat(all.get(0).getTwitterAccount()).isEqualTo(twitterAccount);
        assertThat(all.get(0).getSearchKeyword()).isEqualTo(keyword);
    }
//
//    // menuInfoRemove 테스트
//    /**
//     * 정상 테스트
//     *
//     * 입력값 트위터 계정명과 일치하는 데이터가 DB에 존재하지않음.
//     * 삭제 실행하지 않음.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testMenuInfoRemove01() throws Exception {
//        // 삭제 할 내용 업로드
//        String twitterAccount = "testAccount";
//        String keyword = "testKeyword";
//        InfoRegisterDto targetDto = InfoRegisterDto.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//
//        // 조건 설정
//        List<InfoRegisterDto> requestList = new ArrayList<>();
//        InfoRegisterDto requestDto = InfoRegisterDto.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//        requestList.add(requestDto);
//
//        // Mock 설정
//        Optional<TweetInfoRegister> mockReturnObject = Optional.empty();
//        doReturn(new ArrayList<>()).when(infoRegisterRepository).findAll();
//        doReturn(mockReturnObject).when(infoRegisterRepository).findById(any());
//        doNothing().when(infoRegisterRepository).deleteAll(any());
//
//        String url = "http://localhost:" + port + "/api/account/remove";
//
//        // 테스트 실행
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(requestList)))
//                .andExpect(status().isOk());
//
//        // 결과 체크
//        verify(infoRegisterRepository, times(1)).findAll();
//        verify(infoRegisterRepository, never()).findById(any());
//        verify(infoRegisterRepository, times(1)).deleteAll(any());
//    }
//
//    /**
//     * 정상 테스트
//     *
//     * 입력값 트위터 계정명과 일치하는 데이터가 DB에 존재함.
//     * 입력값 데이터 id와 일치하는 데이터가 DB에 존재함.
//     * 삭제 실행함.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testMenuInfoRemove02() throws Exception {
//        // 삭제 할 내용 업로드
//        String twitterAccount = "testAccount";
//        String keyword = "testKeyword";
//        InfoRegisterDto targetDto = InfoRegisterDto.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//
//        // 조건 설정
//        List<InfoRegisterDto> requestList = new ArrayList<>();
//        InfoRegisterDto requestDto = InfoRegisterDto.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//        requestList.add(requestDto);
//
//        // Mock 설정
//        List<TweetInfoRegister> mockList = new ArrayList<>();
//        TweetInfoRegister mockTweetInfoRegister = TweetInfoRegister.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//        Optional<TweetInfoRegister> mockReturnObject =
//                Optional.of(mockTweetInfoRegister);
//        mockList.add(mockTweetInfoRegister);
//        doReturn(mockList).when(infoRegisterRepository).findAll();
//        doReturn(mockReturnObject).when(infoRegisterRepository).findById(any());
//        doNothing().when(infoRegisterRepository).deleteAll(any());
//
//        String url = "http://localhost:" + port + "/api/account/remove";
//
//        // 테스트 실행
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(requestList)))
//                .andExpect(status().isOk());
//
//        // 결과 체크
//        verify(infoRegisterRepository, times(1)).findAll();
//        verify(infoRegisterRepository, times(1)).findById(any());
//        verify(infoRegisterRepository, times(1)).deleteAll(any());
//    }
//
//    /**
//     * 정상 테스트
//     *
//     * 입력값 트위터 계정명과 일치하는 데이터가 DB에 존재함.
//     * 입력값 데이터 id와 일치하는 데이터가 DB에 존재하지 않음.
//     * 삭제 실행하지 않음.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testMenuInfoRemove03() throws Exception {
//        // 삭제 할 내용 업로드
//        String twitterAccount = "testAccount";
//        String keyword = "testKeyword";
//        InfoRegisterDto targetDto = InfoRegisterDto.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//
//        // 조건 설정
//        List<InfoRegisterDto> requestList = new ArrayList<>();
//        InfoRegisterDto requestDto = InfoRegisterDto.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//        requestList.add(requestDto);
//
//        // Mock 설정
//        List<TweetInfoRegister> mockList = new ArrayList<>();
//        TweetInfoRegister mockTweetInfoRegister = TweetInfoRegister.builder()
//                .twitterAccount(twitterAccount)
//                .searchKeyword(keyword)
//                .build();
//        Optional<TweetInfoRegister> mockReturnObject =
//                Optional.empty();
//        mockList.add(mockTweetInfoRegister);
//        doReturn(mockList).when(infoRegisterRepository).findAll();
//        doReturn(mockReturnObject).when(infoRegisterRepository).findById(any());
//        doNothing().when(infoRegisterRepository).deleteAll(any());
//
//        String url = "http://localhost:" + port + "/api/account/remove";
//
//        // 테스트 실행
//        try {
//            mvc.perform(post(url)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(new ObjectMapper().writeValueAsString(requestList)))
//                    .andExpect(status().isOk());
//        } catch(IllegalArgumentException e) {
//            // 결과 체크
//            assertThat(e.getClass().getSimpleName()).isEqualTo(IllegalArgumentException.class.getSimpleName());
//        } catch(NestedServletException e) {
//            assertThat(e.getClass().getSimpleName()).isEqualTo(NestedServletException.class.getSimpleName());
//        }
//    }
//
//    // menuInfoGetList 테스트
//    /**
//     * 정상 테스트
//     *
//     * DB에 저장된 키워드 데이터를 불러와서 트위터 추출 작업 실행
//     * DB에 저장된 키워드를 볼러움.
//     * 추출 작업 메서드를 볼러움.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testMenuInfoGetList01() throws Exception {
//
//        // Mock 설정
//        String twitterAccount = "testAccount2";
//        String keyword = "testKeyword2";
//        List<TweetInfoRegister> mockList = new ArrayList<>();
//        TweetInfoRegister mockObject =
//                TweetInfoRegister.builder().twitterAccount(twitterAccount)
//                        .searchKeyword(keyword).build();
//        mockList.add(mockObject);
//        doReturn(mockList).when(infoRegisterRepository).findAll();
//        Long returnValue = 1L;
//        doReturn(returnValue).when(twitterLoadingService).infoGetList(mockList);
//
//        String url = "http://localhost:" + port + "/api/get/info";
//
//        // 테스트 실행
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        // 결과 체크
//        verify(infoRegisterRepository, times(1)).findAll();
//        verify(twitterLoadingService, times(1)).infoGetList(mockList);
//    }
//
//    // menuInfoGetListRegister 테스트
//    /**
//     * 정상 테스트
//     *
//     * DB에 저장된 키워드 데이터를 불러와서 트위터 추출 작업 실행
//     * DB에 저장된 키워드를 볼러움.
//     * 추출 작업 메서드를 볼러움.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testMenuInfoGetListRegister01() throws Exception {
//        // 조건 설정
//        String broadcastTitle = "broadcastTitle";
//        String broadcastContext = "broadcastContext";
//        String broadcastTag = "broadcastTag";
//        LocalDateTime broadcastDate = LocalDateTime.now().withNano(0);
//        String tweetAccount = "tweetAccount";
//        String source = "source";
//        InfoGetListRegisterDto requestDto = InfoGetListRegisterDto.builder()
//                .broadcastTitle(broadcastTitle)
//                .broadcastContext(broadcastContext)
//                .broadcastTag(broadcastTag)
//                .broadcastDate(broadcastDate)
//                .tweetAccount(tweetAccount)
//                .source(source)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/info/register";
//
//        // 테스트 실행
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(List.of(requestDto))))
//                .andExpect(status().isOk());
//
//        // 결과 비교
//        List<BroadcastInfo> all = broadcastInfoRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(broadcastTitle);
//        assertThat(all.get(0).getContext()).isEqualTo(broadcastContext);
//        assertThat(all.get(0).getTag()).isEqualTo(broadcastTag);
//        assertThat(all.get(0).getBroadcastDate()).isEqualTo(broadcastDate);
//        assertThat(all.get(0).getTweetAccount()).isEqualTo(tweetAccount);
//        assertThat(all.get(0).getSource()).isEqualTo(source);
//    }

//    // menuInfoCheckUpdate 테스트
//    @Test
//    public void testMenuInfoCheckUpdate01() throws Exception {
//        // 조건 설정
//        InfoGetListRegisterDto requestDto = InfoGetListRegisterDto.builder()
//                .broadcastTitle("1")
//                .broadcastContext("2")
//                .broadcastTag("3")
//                .broadcastDate(LocalDateTime.now())
//                .tweetAccount("4")
//                .source("5")
//                .build();
//        broadcastInfoRepository.save(requestDto.toEntity());
//
//        // 갱신 대상 id 습득용
//        List<BroadcastInfo> testList = broadcastInfoRepository.findAll();
//
//        String broadcastTitle = "broadcastTitle";
//        String broadcastContext = "broadcastContext";
//        String broadcastTag = "broadcastTag";
//        LocalDateTime broadcastDate = LocalDateTime.now().withNano(0);
//        String tweetAccount = "tweetAccount";
//        String source = "source";
//        InfoGetListRegisterDto requestDto2 = InfoGetListRegisterDto.builder()
//                .id(testList.get(0).getId().toString())
//                .broadcastTitle(broadcastTitle)
//                .broadcastContext(broadcastContext)
//                .broadcastTag(broadcastTag)
//                .broadcastDate(broadcastDate)
//                .tweetAccount(tweetAccount)
//                .source(source)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/info/update";
//
//        // 테스트 실행
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(List.of(requestDto2))))
//                .andExpect(status().isOk());
//
//        // 결과 비교
//        List<BroadcastInfo> all = broadcastInfoRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(broadcastTitle);
//        assertThat(all.get(0).getContext()).isEqualTo(broadcastContext);
//        assertThat(all.get(0).getTag()).isEqualTo(broadcastTag);
//        assertThat(all.get(0).getBroadcastDate()).isEqualTo(broadcastDate);
//        assertThat(all.get(0).getTweetAccount()).isEqualTo(tweetAccount);
//        assertThat(all.get(0).getSource()).isEqualTo(source);
//    }
//
//    // menuInfoCheckDelete 테스트
//    @Test
//    public void testMenuInfoCheckDelete01() throws Exception {
//        // 조건 설정
//        InfoGetListRegisterDto requestDto = InfoGetListRegisterDto.builder()
//                .broadcastTitle("1")
//                .broadcastContext("2")
//                .broadcastTag("3")
//                .broadcastDate(LocalDateTime.now())
//                .tweetAccount("4")
//                .source("5")
//                .build();
//        broadcastInfoRepository.save(requestDto.toEntity());
//
//        // 삭제 대상 id 습득용
//        List<BroadcastInfo> testList = broadcastInfoRepository.findAll();
//        String id = testList.get(0).getId().toString();
//        String url = "http://localhost:" + port + "/api/info/delete";
//
//        // 테스트 실행
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(List.of(id))))
//                .andExpect(status().isOk());
//
//        // 결과 비교
//        List<TweetInfoRegister> all = infoRegisterRepository.findAll();
//        assertThat(all.size()).isEqualTo(0);
//    }
}
