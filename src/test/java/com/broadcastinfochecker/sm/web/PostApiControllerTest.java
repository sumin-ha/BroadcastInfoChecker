package com.broadcastinfochecker.sm.web;

import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegisterRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostApiControllerTest {

    @Autowired
    TweetInfoRegisterRepository infoRegisterRepository;

    @After
    public void cleanUp() {
        infoRegisterRepository.deleteAll();
    }

    @Test
    public void loadingTest() {
        infoRegisterRepository.save(TweetInfoRegister.builder()
                .twitterAccount("@Taka_Ayase")
                .searchKeyword("テストキーワード")
                .build());

        List<TweetInfoRegister> infoRegisterList = infoRegisterRepository.findAll();

        TweetInfoRegister infoRegister = infoRegisterList.get(0);
        assertThat(infoRegister.getTwitterAccount()).isEqualTo("@Taka_Ayase");
        assertThat(infoRegister.getSearchKeyword()).isEqualTo("テストキーワード");
    }
}
