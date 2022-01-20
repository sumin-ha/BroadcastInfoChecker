package com.broadcastinfochecker.sm.service;

import com.broadcastinfochecker.sm.domain.posts.BroadcastInfoTemp;
import com.broadcastinfochecker.sm.domain.posts.BroadcastInfoTempRepository;
import com.broadcastinfochecker.sm.domain.posts.TweetInfoRegister;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.endpoints.AdditionalParameters;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.dto.tweet.TweetV2;
import io.github.redouane59.twitter.dto.user.UserV2;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 트위터 관련 된 처리를 담당하는 서비스
 */
@PropertySource("classpath:../application-test.properties")
@RequiredArgsConstructor
@Service
public class TwitterService {

    @Value("${twitter.apiKey}")
    private String apiKey;
    @Value("${twitter.apiSecretKey}")
    private String apiSecretKey;
    @Value("${twitter.accessToken}")
    private String accessToken;
    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    private final BroadcastInfoTempRepository broadcastInfoTempRepository;

    // 등록 된 계정 정보를 이용, 트위터 데이터 추출하는 메서드
    public Long infoGetList(List<TweetInfoRegister> infoRegisterDtoList) {
        TwitterClient twitterClient = getTwitterClient(accessToken, accessTokenSecret, apiKey, apiSecretKey);

        // 현재 시간을 기준으로 7일 전 트윗을 불러오게 함.
        // 파라메터 클래스 설정.
        LocalDateTime endLocalDateTime = LocalDateTime.now();
        LocalDateTime startLocalDateTime = endLocalDateTime.minusDays(7);
        AdditionalParameters additionalParameters = AdditionalParameters.builder()
                .startTime(startLocalDateTime)
                .endTime(endLocalDateTime)
                .build();

        // DB에 저장할 임시 추출 데이터 리스트
        List<BroadcastInfoTemp> broadcastInfoTempList = new ArrayList<>();

        for(TweetInfoRegister source : infoRegisterDtoList) {
            // 유저 id를 습득. (혹시나 유저네임에 @가 붙어있다면 떼어내줌)
            UserV2 userV2 = twitterClient.getUserFromUserName(source.getTwitterAccount().replace("@", ""));

            // 유저가 없으면 다음 아이디를 탐색
            if(userV2.getData() == null) {
                continue;
            }

            // 타임라인 습득
            TweetList tweetList =
                    twitterClient.getUserTimeline(userV2.getId(), additionalParameters);

            // 키워드 리스트 습득
            String[] strArr = source.getSearchKeyword().replace(" ", "").split(",");

            // 습득한 트윗에 키워드가 들어가 있는지 체크.
            for(TweetV2.TweetData tweet : tweetList.getData()) {
                for(String str : strArr) {
                   if(tweet.getText().contains(str)) {
                       // 검색 키워드와 일치하는 트윗이라면
                       // 해당하는 트윗은 가볍게 가공하여, DB에 저장하기 위한 리스트에 저장
                       broadcastInfoTempList.add(BroadcastInfoTemp.builder()
                               .tweetAccount(source.getTwitterAccount())
                               .context(tweet.getText())
                               .source("https://twitter.com/" + source.getTwitterAccount() +"/status/" + tweet.getId())
                               .build());
                       System.out.println("text line : " + tweet.getText().length());
                       System.out.println("text : " + tweet.getText());
                       break;
                   }
                }
            }
        }
        // 획득한 트윗 수 체크
        if(broadcastInfoTempList.size() == 0) {
            // 0건 획득일 경우, 더이상 처리를 하지 않고 반환함
            return 0L;
        }

        // 이미 존재하던 임시 추출 데이터를 삭제
        broadcastInfoTempRepository.deleteAll();
        // 정제된 임시 추출 데이터 리스트를 DB에 저장
        broadcastInfoTempRepository.saveAll(broadcastInfoTempList);

        return 1L;
    }

    // 트위터에 텍스트를 포스트
    public void twitterPost(String str) {
        TwitterClient twitterClient = getTwitterClient(accessToken, accessTokenSecret, apiKey, apiSecretKey);
        twitterClient.postTweet(str);
    }

    // 트위터 api 획득
    public TwitterClient getTwitterClient(String accessToken, String accessTokenSecret,
                                          String apiKey, String apiSecretKey) {
        return new TwitterClient(TwitterCredentials.builder()
                .accessToken(accessToken)
                .accessTokenSecret(accessTokenSecret)
                .apiKey(apiKey)
                .apiSecretKey(apiSecretKey)
                .build());
    }
}
