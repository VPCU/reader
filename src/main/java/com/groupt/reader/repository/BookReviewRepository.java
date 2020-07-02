package com.groupt.reader.repository;

import com.groupt.reader.model.BookReviewEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Long> {
    List<BookReviewEntity> findByRidLessThanEqual(Long id, Sort sort);
    List<BookReviewEntity> findByRidGreaterThanEqual(Long id, Sort sort);
}
