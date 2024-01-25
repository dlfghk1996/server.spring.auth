package server.spring.auth.common.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import server.spring.auth.common.dto.Response;
import server.spring.auth.common.enums.ResponseCode;

@Slf4j
// 인증예외처리 : 미인증 or 회원정보오류
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {

        log.debug("** AuthenticationEntryPoint 인증 에러 **");
        log.error(authException.getMessage(), authException);
        Object filterException = request.getAttribute("authFilterException");


//        if (filterException instanceof ResponseCode) {
//
//            ResponseCode responseCode = (ResponseCode) filterException;
//
//            Response<Object> result = new Response<>(responseCode);
//
//            response.setStatus(HttpStatus.OK.value());
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
//            System.out.println("here1");
//            return;
//        }
//
//
//        ResponseCode responseCode = (ResponseCode) filterException;
//
//        Response<Object> result = new Response<>(ResponseCode.ERROR_AUTHENTICAION);
//
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
//        System.out.println("here1");
//        return;

        Response<Object> result = new Response<>(null, ResponseCode.ERROR_AUTHENTICAION);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
