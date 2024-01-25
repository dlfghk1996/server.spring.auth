package server.spring.auth.user.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import server.spring.auth.common.enums.BaseEnum;

public enum MemberStatus implements BaseEnum {
  ACTIVATED("ACTIVATED", "활성화 또는 연결 상태"),
  INACTIVATED("INACTIVATED", "연결 끊어진 상태"),
  DELETED("DELETED", "탈퇴 상태"),
  BLOCKED("BLOCKED", "회원 블락 상태"),
  SLEPT("SLEPT", "휴먼 회원");

  @JsonValue private final String code;
  private final String label;

  MemberStatus(String code, String label) {
    this.code = code;
    this.label = label;
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
