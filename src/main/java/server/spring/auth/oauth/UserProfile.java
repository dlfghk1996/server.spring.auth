package server.spring.auth.oauth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import server.spring.auth.user.domain.User;

@ToString
@Data
public class UserProfile {
    private final String oauthId;
    private final String email;
    private final String name;
    private final String imageUrl;

    @Builder
    public UserProfile(String oauthId, String email, String name, String imageUrl) {
        this.oauthId = oauthId;
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
    }

//    public User toUser() {
//        return Member.builder()
//            .oauthId(oauthId)
//            .email(email)
//            .name(name)
//            .imageUrl(imageUrl)
//            .role(Role.GUEST)
//            .build();
//    }
}
