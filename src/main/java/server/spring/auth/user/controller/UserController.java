package server.spring.auth.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.spring.auth.common.dto.Response;
import server.spring.auth.common.enums.ResponseCode;
import server.spring.auth.token.controller.dto.TokenDTO;
import server.spring.auth.token.domain.PrincipalDetails;
import server.spring.auth.user.controller.dto.LoginDTO;
import server.spring.auth.user.controller.dto.UserDTO;
import server.spring.auth.user.service.UserService;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    // 로그인
    @GetMapping("")
    public Response<UserDTO> get(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(principalDetails.toString());
        return new Response<>(userService.get(principalDetails.getUser().getId()), ResponseCode.OK);
    }
}
