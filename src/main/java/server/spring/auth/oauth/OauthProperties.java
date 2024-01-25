package server.spring.auth.oauth;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ToString
@Getter
@Component
@ConfigurationProperties(prefix = "oauth2")
public class OauthProperties {

    private final Map<String, OauthProvider> provider = new HashMap<>();

    @Data
    @ToString
    public static class OauthProvider {
        private String clientId;
        private String clientSecret;
        private String redirectUrl;
        private String tokenUrl;
        private String userInfoUrl;
        private String userNameAttribute;
    }
}
