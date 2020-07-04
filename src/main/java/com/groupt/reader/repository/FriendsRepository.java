package com.groupt.reader.repository;

import com.groupt.reader.model.Friends;
import com.groupt.reader.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    Boolean existsByUser1AndUser2(Long user1, Long user2);
    @Modifying
    @Transactional
    void deleteAllByUser1AndUser2(Long user1, Long user2);

}
