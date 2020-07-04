package com.groupt.reader.repository;

import com.groupt.reader.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByUname(String username);
    boolean existsByUname(String username);
    @Query("select u from UserEntity u , Friends f where f.user1 = ?1 and u.uid = f.user2")
    List<UserEntity> getAllFriends(Long uid);
}