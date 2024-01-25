package server.spring.auth.common.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import server.spring.auth.common.dto.Response;
import server.spring.auth.common.enums.ResponseCode;

// 권한 예외처리
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException exception) throws IOException {

        log.debug("** AccessDenedHandler 권한 에러 **");
        log.error(exception.getMessage(), exception);

//        //response에 넣기
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        response.setCharacterEncoding("utf-8");
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        try (OutputStream os = response.getOutputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(os, new Response<>(ResponseCode.ERROR_INVALID_ACCESS));
//            os.flush();
//        }
//
        Response<Object> result = new Response<>(null, ResponseCode.ERROR_NO_ACCESS_DENIED);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));

        return;
        // }

        //response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");

    }
}
