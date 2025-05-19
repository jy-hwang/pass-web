package com.fastcampus.pass.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BulkPassRepository extends JpaRepository<BulkPassEntity, Integer> {
  @Query(value = "SELECT b" +
      "             FROM BulkPassEntity b " +
      "            ORDER BY b.startedAt DESC")
  List<BulkPassEntity> findAllOrderByStartedAtDesc();
}
