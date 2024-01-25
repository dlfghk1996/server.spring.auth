package server.spring.auth.user.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import server.spring.auth.common.enums.BaseEnum;

public enum LoginType implements BaseEnum {
  MOMMA("ORIGIN", "일반로그인"),
  KAKAO("KAKAO", "카카오"),
  NAVER("NAVER", "네이버"),
  APPLE("APPLE", "애플"),
  ADMIN("ADMIN", "관리자");

  @JsonValue private final String code;
  private final String label;

  LoginType(String code, String label) {
    this.code = code;
    this.label = label;
  }

  public static LoginType getLoginType(String provider) {
    if (LoginType.MOMMA.getCode().equals(provider)) {
      return LoginType.MOMMA;
    }
    if (LoginType.KAKAO.getCode().equals(provider)) {
      return LoginType.KAKAO;
    }
    if (LoginType.NAVER.getCode().equals(provider)) {
      return LoginType.NAVER;
    }
    return null;
  }

  public String getCode() {
    return this.code;
  }

  @Override
  public String getName() {
    return this.name();
  }

  @Override
  public String getLabel() {
    return this.label;
  }
}
