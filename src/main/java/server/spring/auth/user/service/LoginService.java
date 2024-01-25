package server.spring.auth.user.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.spring.auth.common.config.exception.ServiceException;
import server.spring.auth.common.dto.Response;
import server.spring.auth.common.enums.ResponseCode;
import server.spring.auth.token.controller.dto.TokenDTO;
import server.spring.auth.token.domain.PrincipalDetails;
import server.spring.auth.token.service.TokenService;
import server.spring.auth.user.controller.dto.LoginDTO;
import server.spring.auth.user.controller.dto.LoginDTO.*;
import server.spring.auth.user.enums.RoleType;
import server.spring.auth.user.repository.UserRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class LoginService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public TokenDTO signIn(LoginRequest request) {
        // 인증을 요청하는 사용자 값
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(request.getUserId(),
                request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        if (principalDetails != null) {
            return tokenService.createAccessToken(principalDetails.getRole(), principalDetails.getUser().getId());
        } else{
            throw new ServiceException(ResponseCode.MEMBER_INVALID_ACCOUNT);
        }
    }
}
