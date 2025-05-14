package com.fastcampus.pass.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {
  @Query(value = "SELECT p" +
      "             FROM PassEntity p " +
      "             JOIN FETCH p.packageEntity " +
      "            WHERE p.userId = :userId " +
      "            ORDER BY p.endedAt DESC NULLS FIRST ")
  List<PassEntity> findByUserId(String userId);

}
