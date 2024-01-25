package server.spring.auth.token.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import server.spring.auth.common.config.exception.ServiceException;
import server.spring.auth.common.enums.ResponseCode;
import server.spring.auth.token.controller.dto.TokenDTO;
import server.spring.auth.token.controller.dto.TokenDTO.*;
import server.spring.auth.token.util.JwtTokenProvider;
import server.spring.auth.user.enums.RoleType;


@Slf4j
@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper mapper;

    public TokenDTO createAccessToken(List<RoleType> roleType, Long userId) {
        Map<String, String> accessTokenMap = jwtTokenProvider.createAccessToken(roleType, userId);

        Map<String, String> refreshTokenMap = jwtTokenProvider.createRefreshToken();

        accessTokenMap.putAll(refreshTokenMap);
        return this.setterToken(accessTokenMap, new TokenDTO());
    }

    // Token Setter
    private TokenDTO setterToken(Map<String, String> map, TokenDTO token) {

        try {
            for (Map.Entry<String, String> entryset : map.entrySet()) {
                Field[] fields = token.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);  //private 변수를 public 변수로 변경

                    String fieldName = field.getName();

                    boolean isSameType = entryset.getKey().equals(fieldName);
                    boolean isSameName = entryset.getValue().getClass().equals(field.getType());

                    if (isSameName && isSameType) {
                        field.set(token, map.get(fieldName));
                    }
                }
            }

            return token;
        } catch (IllegalAccessException e) {
            log.error("Reflection 을 이용한 Token Setter 시도 중 에러", e.getLocalizedMessage());
            throw new ServiceException(ResponseCode.UNKNOWN);
        }
    }
}
