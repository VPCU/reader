package com.groupt.reader.repository;

import com.groupt.reader.model.UserLikeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserLikeReviewRepository extends JpaRepository<UserLikeReview, Long> {
    boolean existsByUserAndReview(Long user, Long review);
    void deleteAllByUserAndReview(Long user, Long review);
    int countByUser(Long user);
    int countByReview(Long review);
    @Query("select review from UserLikeReview where user = ?1")
    List<Long> getReviewByUser(Long user);
}
