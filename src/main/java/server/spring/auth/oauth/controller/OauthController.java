package server.spring.auth.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.spring.auth.common.dto.Response;
import server.spring.auth.common.enums.ResponseCode;
import server.spring.auth.oauth.OauthProperties;
import server.spring.auth.oauth.dto.LoginDTO;
import server.spring.auth.oauth.service.OauthService;

@RestController
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;
    private final OauthProperties oauthProperties;

    @GetMapping("/login/oauth/{provider}")
    public Response<LoginDTO> login(@PathVariable String provider, @RequestParam String code) {
        System.out.println(oauthProperties.toString());
        LoginDTO loginResponse = oauthService.login(provider, code);
        return new Response(loginResponse, ResponseCode.OK);
    }

    // 인증코드 받는 API -TEST
    @GetMapping("/oauth/api/code")
    public Response<String> code(@RequestParam String code) {
        return new Response(code, ResponseCode.OK);
    }
}
