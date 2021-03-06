package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfoRepository;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegisterRepository;
import com.broadcastinfochecker.sm.service.TwitterService;
import com.broadcastinfochecker.sm.web.dto.InfoRegisterDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest2 {

    @LocalServerPort
    private int port;

    private MockMvc mvc;

    @MockBean
    private TwitterService twitterLoadingService;

    @MockBean
    private TweetInfoRegisterRepository infoRegisterRepository;

    @MockBean
    private BroadcastInfoRepository broadcastInfoRepository;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    // testMenuInfoRemove03??? ???????????? Exception ?????????.
    //
    /**
     * ?????? ?????????
     *
     * ????????? ????????? ???????????? ???????????? ???????????? DB??? ?????????.
     * ????????? ????????? id??? ???????????? ???????????? DB??? ???????????? ??????.
     * ?????? ???????????? ??????.
     *
     * @throws Exception
     */
    @Test
    public void testMenuInfoRemove03() throws Exception {
        // ?????? ??? ?????? ?????????
        String twitterAccount = "testAccount";
        String keyword = "testKeyword";
        InfoRegisterDto targetDto = InfoRegisterDto.builder()
                .twitterAccount(twitterAccount)
                .searchKeyword(keyword)
                .build();

        // ?????? ??????
        List<InfoRegisterDto> requestList = new ArrayList<>();
        InfoRegisterDto requestDto = InfoRegisterDto.builder()
                .twitterAccount(twitterAccount)
                .searchKeyword(keyword)
                .build();
        requestList.add(requestDto);

        // Mock ??????
        List<TweetInfoRegister> mockList = new ArrayList<>();
        TweetInfoRegister mockTweetInfoRegister = TweetInfoRegister.builder()
                .twitterAccount(twitterAccount)
                .searchKeyword(keyword)
                .build();
        Optional<TweetInfoRegister> mockReturnObject =
                Optional.empty();
        mockList.add(mockTweetInfoRegister);
        doReturn(mockList).when(infoRegisterRepository).findAll();
        doReturn(mockReturnObject).when(infoRegisterRepository).findById(any());
        doNothing().when(infoRegisterRepository).deleteAll(any());

        String url = "http://localhost:" + port + "/api/account/remove";

        // ????????? ??????
        try {
            mvc.perform(post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(requestList)))
                    .andExpect(status().isOk());
        } catch(IllegalArgumentException e) {
            // ?????? ??????
            assertThat(e.getClass().getSimpleName()).isEqualTo(IllegalArgumentException.class.getSimpleName());
        } catch(NestedServletException e) {
            assertThat(e.getClass().getSimpleName()).isEqualTo(NestedServletException.class.getSimpleName());
        }
    }
}
