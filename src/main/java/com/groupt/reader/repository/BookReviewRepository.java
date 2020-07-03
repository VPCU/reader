package com.groupt.reader.repository;

import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Long> {
    List<BookReviewEntity> findByRidLessThanEqualAndDisabled(Long id, Boolean disabled, Pageable page);
    List<BookReviewEntity> findByRidGreaterThanEqualAndDisabled(Long id, Boolean disabled, Pageable page);
    List<BookReviewEntity> findByAuthorAndDisabled(UserEntity user, Boolean disabled);
    List<BookReviewEntity> findAllByDisabled(Boolean disabled);
}
