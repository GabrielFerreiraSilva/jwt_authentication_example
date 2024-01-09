package com.gabriel.jwt_authentication_example.enums;

import lombok.Getter;

@Getter
public enum UserRole {
  ROLE_ADMIN("System's administrator"),
  ROLE_STANDARD_USER("System's standard user");

  private final String description;

  UserRole(String description) {
    this.description = description;
  }
}
