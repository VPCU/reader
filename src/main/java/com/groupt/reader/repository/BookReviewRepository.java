package com.groupt.reader.repository;

import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.PermEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Long> {
}
