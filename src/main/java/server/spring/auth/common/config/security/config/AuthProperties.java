package server.spring.auth.common.config.security.config;

public class AuthProperties {
  public static final String AUTH_HEADER_NAME = "Authorization";

  // TODO: 2021/02/18 테스트 기간 동안 token 시간 조정
  //  public static final long ACCESS_TOKEN_EXPIRATION_TIME = 10 * 60 * 1000L; // 10분
  public static final long ACCESS_TOKEN_EXPIRATION_TIME = 90 * 24 * 60 * 60 * 1000L; // 90일
  //  public static final long REFRESH_TOKEN_EXPIRATION_TIME = 30 * 24 * 60 * 60 * 1000L; // 한달
  public static final long REFRESH_TOKEN_EXPIRATION_TIME = 90 * 24 * 60 * 60 * 1000L; // 90일

  public static final String KEY_PAIR_ALIAS = "jwtkey";
  public static final String KEY_PAIR_SECRET = "jwtauth";
  public static final String KEY_PAIR_FILE_NAME = "jwtKey.jks";
}
