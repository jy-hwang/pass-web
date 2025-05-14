package com.fastcampus.pass.repository.pass;

import lombok.Getter;

public enum PassStatus {
  READY("준비"), PROGRESSED("실행 중"), EXPIRED("만료됨");
  @Getter
  private final String description;

  PassStatus(String description) {
    this.description = description;
  }

}
