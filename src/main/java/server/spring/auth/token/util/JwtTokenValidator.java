package server.spring.auth.token.util;


import io.jsonwebtoken.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import server.spring.auth.common.config.exception.ServiceException;
import server.spring.auth.common.enums.ResponseCode;
import server.spring.auth.token.domain.PrincipalDetails;
import server.spring.auth.token.domain.enums.ClaimsKey;
import server.spring.auth.user.domain.User;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenValidator {

    private final JwtTokenProvider jwtUtil;
    //private final JwtKeyUtil keyUtil;


    // 토큰 유효성검사
    public boolean validateToken(String token) {
        // jwt 복호화
        try {
//            JWT.require(algorithm)
//                    .build()
//                    .verify(jwt);
            Jwts.parserBuilder().setSigningKey(jwtUtil.getPublicKey())
                .build()
                .parseClaimsJws(token);

            return true;
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            System.out.println(e);
            log.error("구조적인 문제 및 암호화되지 않은 JWT가 전달됬을 때 발생하는 오류", e.getLocalizedMessage());
            throw new ServiceException(ResponseCode.ACCESS_TOKEN_MISMATCH_PATTERN);
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.", e.getLocalizedMessage());
            throw new ServiceException(ResponseCode.TOKEN_EXPIRED);
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println(e);
            log.error("잘못된 JWT 서명입니다.", e.getLocalizedMessage());
            throw new ServiceException(ResponseCode.ACCESS_TOKEN_INVALID_REQUEST);
        } catch (Exception e) {
            throw new ServiceException(ResponseCode.ACCESS_TOKEN_INVALID_REQUEST);
        }
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String reqToken) {
        log.info("****[토큰 인증 정보 검사] getAuthentication 호출 ******");
        Jws<Claims> jsw = this.getJws(reqToken);

        System.out.println(jsw.getBody().get(ClaimsKey.USERID_KEY.getName()));
        System.out.println(jsw.getBody().get(ClaimsKey.ROLE_KEY.getName()));

        PrincipalDetails principalDetails = new PrincipalDetails();
        Long userId = Long.valueOf(
            String.valueOf(jsw.getBody().get(ClaimsKey.USERID_KEY.getName())));
        List<String> userRoleList = (List<String>) jsw.getBody().get(ClaimsKey.ROLE_KEY.getName());

        principalDetails.setUserWithId(userId);
        principalDetails.setRoleWithList(userRoleList);
        return new UsernamePasswordAuthenticationToken(principalDetails, null,
            principalDetails.getAuthorities());
    }

    // 토큰 복호화 : Claims 추출
    public Jws<Claims> getJws(String token) {
        try {
            // private key 로 public key 추출
            // jws 파싱
            Jws<Claims> jws =
                    Jwts
                            .parserBuilder()
                            // jws 서명 증명을 위해 사용할 key
                            .setSigningKey(jwtUtil.getPrivateKey())
                            .build()
                            .parseClaimsJws(token);
            return jws;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new ServiceException(ResponseCode.UNKNOWN);
        }
    }
}
