package server.spring.auth.user.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    private String userId;
    private String userPassword;

    @Data
    public static class LoginRequest {
        @NotNull
        @NotEmpty
        private String userId;

        @NotNull
        @NotEmpty
        private String password;

    }

    @Data
    public static class SignUpRequest {
        @NotNull
        @NotEmpty
        private String userName;

        @NotNull
        @NotEmpty
        private String userId;

        @NotNull
        @NotEmpty
        private String userPassword;
    }
}
