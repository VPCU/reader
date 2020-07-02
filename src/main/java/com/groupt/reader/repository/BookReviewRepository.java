package com.groupt.reader.repository;

import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Long> {
    List<BookReviewEntity> findByRidLessThanEqual(Long id, Pageable page);
    List<BookReviewEntity> findByRidGreaterThanEqual(Long id, Pageable page);
    List<BookReviewEntity> findByAuthor(UserEntity user);
}
