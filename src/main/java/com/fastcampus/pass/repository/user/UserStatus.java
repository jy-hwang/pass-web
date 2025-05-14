package com.fastcampus.pass.repository.user;

import lombok.Getter;

public enum UserStatus {
  ACTIVE("활성화"), INACTIVE("비활성화");

  @Getter
  private final String description;

  UserStatus(String description) {
    this.description = description;
  }

}
