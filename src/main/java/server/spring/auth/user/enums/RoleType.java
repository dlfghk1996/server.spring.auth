package server.spring.auth.user.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.spring.auth.common.enums.BaseEnum;

public enum RoleType implements BaseEnum {
  // 일반
  ANONYMOUS("ROLE_ANONYMOUS", "익명", 0),
  GUEST("ROLE_GUEST", "손님", 1),
  USER("ROLE_USER", "회원", 2),
  SILVER("ROLE_SILVER", "실버 등급", 5),
  GOLD("ROLE_GOLD", "골드 등급", 6),
  VIP("ROLE_VIP", "VIP 등급", 10);

  @JsonValue private final String code;
  private final String label;
  private final int level;

  RoleType(String code, String label, int level) {
    this.code = code;
    this.label = label;
    this.level = level;
  }

  public int getLevel() {
    return this.level;
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

  private static final Map<String, RoleType> BY_CODE = new HashMap<>();

  static {
    for (RoleType r: values()) {
      BY_CODE.put(r.code, r);
    }
  }
  public static RoleType valueOfCode(String label) {
    return BY_CODE.get(label);
  }
}
