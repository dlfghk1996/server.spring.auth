package server.spring.auth.token.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.util.http.parser.Authorization;
import server.spring.auth.common.dto.AbstractData;

@Data
public class TokenDTO {

    private String accessToken;

    private String accessTokenExpiredAt;


    private String refreshToken;


    private String refreshTokenExpiredAt;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class TokenRequest extends AbstractData {
        //인증 과정에 대한 구분값으로 어떠한 형태의 결과값을 받을지 명시.
        // 항상 "authorization_code"라는 고정된 문자열 사용
        private String grant_type;

        // Developer Console에서 등록한 client id값
        private String client_id;

        // Developer Console에서 등록한 client secret값
        private String client_secret;

        // Authorization Code
        // Authorization Code는 Access Token을 발급하는 데 사용되는 일회성 코드로서, 유효 기간을 짧게 하는 것이 좋다.
        private String code;
    }

}
