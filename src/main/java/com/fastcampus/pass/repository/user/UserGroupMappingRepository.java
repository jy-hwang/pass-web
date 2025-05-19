package com.fastcampus.pass.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMappingEntity, Integer> {
  List<UserGroupMappingEntity> findByUserGroupId(String userGroupId);

  @Query(" SELECT DISTINCT u.userGroupId " +
      "      FROM UserGroupMappingEntity u " +
      "     ORDER BY u.userGroupId ")
  List<String> findDistinctUserGroupId();
}