package com.groupt.reader.repository;

import com.groupt.reader.model.UserEkilReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface UserEkilReviewRepository extends JpaRepository<UserEkilReview, Long> {
    boolean existsByUserAndReview(Long user, Long review);
    @Modifying
    @Transactional
    void deleteAllByUserAndReview(Long user, Long review);
    int countByReview(Long review);
}
