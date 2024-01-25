package server.spring.auth.token.domain.enums;

import server.spring.auth.common.enums.BaseEnum;

public enum ClaimsKey implements BaseEnum {
  USERID_KEY("userId", "pk"),
  ROLE_KEY("role", "role");

  private final String name;
  private final String label;

  ClaimsKey(String name, String label) {
    this.name = name;
    this.label = label;
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
