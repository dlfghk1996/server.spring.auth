package server.spring.auth.common.enums;


import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public enum ResponseCode implements BaseEnum{
    /** ** [ 1000 ~ 1999 ] : SUCCESS *** */
    OK(0, "success", SC_OK),
    SUCCESS(1000, "success", SC_OK),

    /** ** [ 2000 ~ 2999 ] : INVALID_REQUEST *** */
    INVALID_REQUEST(2000, "잘 못 된 요청입니다.", 2000),

    // Token error
    ACCESS_TOKEN_NOT_FOUND(2101, "Access Token을 찾을 수 없습니다.", 2101),
    ACCESS_TOKEN_INVALID_REQUEST(2100, "잘못된 Access Token입니다.", 2100),
    TOKEN_EXPIRED(2102, "이미 만료된 토큰입니다.", 2101),
    ACCESS_TOKEN_MISMATCH_PATTERN(2103, "지원하지않는 토큰입니다.", 2102),

    REFRESH_TOKEN_NOT_FOUND(2111, "Refresh Token을 찾을 수 없습니다.", SC_OK),
    REFRESH_TOKEN_MISMATCH_PATTERN(2112, "잘못된 refresh token입니다.", SC_OK),
    REFRESH_TOKEN_EXPIRED(2113, "이미 만료된 Refresh Token입니다.", SC_OK),


    // Auth Error
    ERROR_NO_ACCESS_DENIED(4000, "접근권한이 없습니다.", SC_FORBIDDEN),
    ERROR_AUTHENTICAION(4001, "인증정보가 없습니다.", SC_UNAUTHORIZED),


    // Member Error
    MEMBER_INVALID_ACCOUNT(3101, "아이디 또는 비밀번호를 확인해 주세요.", SC_OK),
    //MEMBER_NOT_FOUND(3102, "일치하는 정보가 없습니다.", SC_OK);


    ERROR(9000, "ERROR", SC_OK),
    UNKNOWN(9999, "알 수 없는 오류입니다.", SC_INTERNAL_SERVER_ERROR);

    private final int code;
    private final String label;
    private final int httpStatusCode;

    ResponseCode(int code, String label, int httpStatusCode) {
        this.code = code;
        this.label = label;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
