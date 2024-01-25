package server.spring.auth.user.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import server.spring.auth.common.config.exception.ServiceException;
import server.spring.auth.common.enums.ResponseCode;
import server.spring.auth.token.controller.dto.TokenDTO;
import server.spring.auth.token.service.TokenService;
import server.spring.auth.user.controller.dto.UserDTO;
import server.spring.auth.user.domain.User;
import server.spring.auth.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public UserDTO get(Long id) {
         User user = userRepository.findById(id)
            .orElseThrow(() -> new ServiceException(ResponseCode.INVALID_REQUEST));
        return mapper.map(user, UserDTO.class);
    }
}
