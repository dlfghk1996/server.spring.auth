package server.spring.auth.token.controller;


import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.spring.auth.common.dto.Response;
import server.spring.auth.token.controller.dto.TokenDTO;
import server.spring.auth.token.controller.dto.TokenDTO.*;
import server.spring.auth.token.service.TokenService;
import server.spring.auth.user.enums.RoleType;

@RequiredArgsConstructor
@RestController
@RequestMapping("/token")
public class TokenController {
    private final TokenService service;


    @GetMapping("")
    public Response<TokenDTO> create(@RequestBody TokenRequest request) {
        // TODO 게스트 토큰간의 구분 방법
        TokenDTO response = service.createAccessToken(Arrays.asList(RoleType.GUEST), 0L);
        return new Response<>(response);
    }


}
