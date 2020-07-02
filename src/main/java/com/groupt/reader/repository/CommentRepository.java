package com.groupt.reader.repository;

import com.groupt.reader.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByReviewId(Long reviewId);
}
