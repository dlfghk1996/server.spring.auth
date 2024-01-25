package server.spring.auth.oauth.service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import server.spring.auth.oauth.OauthAttributes;
import server.spring.auth.oauth.OauthProperties;
import server.spring.auth.oauth.OauthProperties.OauthProvider;
import server.spring.auth.oauth.UserProfile;
import server.spring.auth.oauth.dto.OauthTokenResponse;
import server.spring.auth.oauth.dto.LoginDTO;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class OauthService {

    private static final int REQUEST_TIMEOUT_SECONDS = 10;

    private final OauthProperties oauthProperties;

    public LoginDTO login(String providerName, String code) {
        OauthProvider provider = oauthProperties.getProvider().get(providerName);
        // access token 가져오기
        OauthTokenResponse oauthToken = this.getToken(code, provider);

        System.out.println(oauthToken.toString());
        // 회원정보 가져오기
        UserProfile userProfile = getUserProfile(providerName, oauthToken, provider);
        System.out.println(userProfile.toString());
        // TODO 유저 DB에 저장
        // TODO 토큰 발급
        return null;
    }

    private OauthTokenResponse getToken(String code, OauthProvider provider) {
        return WebClient.create()
            .post()
            .uri(provider.getTokenUrl())
            .headers(header -> {
                header.setBasicAuth(provider.getClientId(), provider.getClientSecret());
                header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            })
            .bodyValue(tokenRequest(code, provider))
            .retrieve()
            .bodyToMono(OauthTokenResponse.class)
            .block(Duration.ofSeconds(REQUEST_TIMEOUT_SECONDS));
    }

    private MultiValueMap<String, String> tokenRequest(String code, OauthProvider provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUrl());
        return formData;
    }


    private UserProfile getUserProfile(String providerName, OauthTokenResponse tokenResponse,
        OauthProvider provider) {
        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
        // TODO 유저 정보(map)를 통해 UserProfile 만들기
        return OauthAttributes.extract(providerName, userAttributes);
    }

    // OAuth 서버에서 유저 정보 map으로 가져오기
    private Map<String, Object> getUserAttributes(OauthProvider provider,
        OauthTokenResponse tokenResponse) {
        return WebClient.create()
            .get()
            .uri(provider.getUserInfoUrl())
            .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
            })
            .block();
    }
}