package com.groupt.reader.repository;

import com.groupt.reader.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByUname(String username);
    boolean existsByUname(String username);
    @Query("select u from UserEntity u , Friends f where f.user1 = ?1 and u.uid = f.user2")
    List<UserEntity> getAllFriends(Long uid);
    @Modifying
    @Transactional
    @Query("update UserEntity u set u.updated = ?2 where u.uid = ?1")
    int update(Long uid, Date date);
    @Query("select u.updated from UserEntity u where u.uid = ?1")
    Date getUpdatedByUid(Long uid);
}