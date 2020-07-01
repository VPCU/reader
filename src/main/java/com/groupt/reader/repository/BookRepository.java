package com.groupt.reader.repository;

import com.groupt.reader.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByNameAndAuthor(String name, String Author);
}
