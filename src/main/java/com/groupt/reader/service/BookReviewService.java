package com.groupt.reader.service;

import com.groupt.reader.dto.BookReviewDto;
import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.NewBookReviewDto;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.BookReviewMapper;
import com.groupt.reader.model.BookEntity;
import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.BookRepository;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        bookReviewEntity.setContent(bookReviewDto.getContent());
        BookEntity book = bookRepository.findByNameAndAuthor(bookReviewDto.getBookName(), bookReviewDto.getBookAuthor());
        if(book == null) {
            book = new BookEntity();
            book.setAuthor(bookReviewDto.getBookAuthor());
            book.setName(bookReviewDto.getBookAuthor());
            bookRepository.save(book);
        }
        bookReviewEntity.setBook(book);
        bookReviewEntity.setCreateTime(new Date());
        bookReviewRepository.save(bookReviewEntity);
        return true;
    }

    public List<BookReviewDto> getAllBookReviews() {
        List<BookReviewDto> ret = new ArrayList<>();
        List<BookReviewEntity> reviews = bookReviewRepository.findAll();
        for(BookReviewEntity review : reviews) {
            ret.add(BookReviewMapper.BookReviewToBookReviewDto(review));
        }
        return ret;
    }
}
