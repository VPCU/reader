package com.groupt.reader.service;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.NewBookReviewDto;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.BookEntity;
import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.BookRepository;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class BookReviewService {

    @Autowired
    BookReviewRepository bookReviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    public boolean newBookReview(NewBookReviewDto bookReviewDto) {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        UserEntity userEntity = userRepository.findByUname(userDto.getUname());
        BookReviewEntity bookReviewEntity = new BookReviewEntity();
        bookReviewEntity.setAuthor(userEntity);
        bookReviewEntity.setTitle(bookReviewDto.getTitle());
        BookEntity book = bookRepository.findByNameAndAuthor(bookReviewDto.getBookName(), bookReviewDto.getBookAuthor());
        bookReviewEntity.setBook(book);
        bookReviewEntity.setCreateTime(new Date());
        bookReviewRepository.save(bookReviewEntity);
        return true;
    }
}
