package com.groupt.reader.repository;

import com.groupt.reader.model.UserEkilReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEkilReviewRepository extends JpaRepository<UserEkilReview, Long> {
    boolean existsByUserAndReview(Long user, Long review);
    void deleteAllByUserAndReview(Long user, Long review);
    int countByReview(Long review);
}
