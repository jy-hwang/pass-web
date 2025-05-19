package com.fastcampus.pass.repository.pass;

import lombok.Getter;

public enum BulkPassStatus {

  READY("준비"), COMPLETED("완료");
  @Getter
  private final String description;

  BulkPassStatus(String description) {
    this.description = description;
  }
}
