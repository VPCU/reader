package com.groupt.reader.service;

import com.groupt.reader.dto.BookReviewDto;
import com.groupt.reader.dto.NewBookReviewDto;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.BookReviewMapper;
import com.groupt.reader.model.*;
import com.groupt.reader.repository.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookReviewService {

    @Autowired
    BookReviewRepository bookReviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserLikeReviewRepository userLikeReviewRepository;

    @Autowired
    UserEkilReviewRepository userEkilReviewRepository;

    public boolean newBookReview(NewBookReviewDto bookReviewDto) {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        UserEntity userEntity = userRepository.findById(userDto.getUid()).get();
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

    public List<BookReviewDto> getSelfBookReviews() {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        UserEntity userEntity = userRepository.findById(userDto.getUid()).get();
        return getBookReviewsByAuthor(userEntity);
    }

    public List<BookReviewDto> getBookReviewsByAuthor(Long uid) {
        return getBookReviewsByAuthor(userRepository.findById(uid).get());
    }

    public List<BookReviewDto> getBookReviewsByAuthor(UserEntity user) {
        List<BookReviewDto> reviews = new ArrayList<>();
        for(BookReviewEntity review :bookReviewRepository.findByAuthorAndDisabled(user, false)) {
            if(!review.getDisabled())
                reviews.add(BookReviewMapper.BookReviewToBookReviewDto(review));
        }
        return reviews;
    }

    public List<BookReviewDto> getBookReviews(int cursor, int limit, boolean desc) {

        List<BookReviewDto> ret = new ArrayList<>();
        List<BookReviewEntity> reviews;
        if(!desc) {
            Sort sort = Sort.sort(BookReviewEntity.class).by(BookReviewEntity::getRid).ascending();
            Pageable pageable = PageRequest.of(0, limit, sort);
            reviews = bookReviewRepository.findByRidGreaterThanEqualAndDisabled((long) cursor, false, pageable);
        } else {
            Sort sort = Sort.sort(BookReviewEntity.class).by(BookReviewEntity::getRid).descending();
            Pageable pageable = PageRequest.of(0, limit, sort);
            reviews = bookReviewRepository.findByRidLessThanEqualAndDisabled((long) cursor, false, pageable);
        }
        for(BookReviewEntity review : reviews) {
            if(!review.getDisabled())
                ret.add(BookReviewMapper.BookReviewToBookReviewDto(review));
        }
        return ret;
    }

    public boolean likeByRid(int rid) {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        return userLikeReviewRepository.existsByUserAndReview(userDto.getUid(), (long)rid);
    }

    public boolean setUserLikeReview(Long user, Long review, boolean like) {
        boolean now = userLikeReviewRepository.existsByUserAndReview(user, review);
        if(now == like) return true;
        if(like) {
            UserLikeReview userLikeReview = new UserLikeReview();
            if(!userRepository.existsById(user) || !bookReviewRepository.existsById(review)) return false;
            userLikeReview.setReview(review);
            userLikeReview.setUser(user);
            userLikeReviewRepository.save(userLikeReview);
            return true;
        } else {
            userLikeReviewRepository.deleteAllByUserAndReview(user, review);
            return true;
        }
    }

    public boolean setSelfLikeReview(Long review, boolean like) {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        return setUserLikeReview(userDto.getUid(), review, like);
    }

    public int countLike() {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        return userLikeReviewRepository.countByUser(userDto.getUid());
    }

    public List<BookReviewDto> getLikes() {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        List<Long> ids = userLikeReviewRepository.getReviewByUser(userDto.getUid());
        List<BookReviewDto> ret = new ArrayList<>();
        for(Long id : ids) {
            Optional<BookReviewEntity> review = bookReviewRepository.findById(id);
            review.ifPresent(bookReviewEntity -> ret.add(BookReviewMapper.BookReviewToBookReviewDto(bookReviewEntity)));
        }
        return ret;
    }

    public BookReviewDto getBookReviewById(Long rid) {
        Optional<BookReviewEntity> book = bookReviewRepository.findById(rid);
        return book.map(BookReviewMapper::BookReviewToBookReviewDto).orElse(null);
    }

    public int countEkil(Long rid) {
        return userEkilReviewRepository.countByReview(rid);
    }

    public boolean setEkil(Long review, boolean like) {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        Long user = userDto.getUid();
        boolean now = userEkilReviewRepository.existsByUserAndReview(user, review);
        if(now == like) return true;
        if(like) {
            UserEkilReview userEkilReview = new UserEkilReview();
            if(!userRepository.existsById(user) || !bookReviewRepository.existsById(review)) return false;
            userEkilReview.setReview(review);
            userEkilReview.setUser(user);
            userEkilReviewRepository.save(userEkilReview);
            return true;
        } else {
            userEkilReviewRepository.deleteAllByUserAndReview(user, review);
            return true;
        }
    }

    public boolean getEkil(long rid) {
        UserDto userDto = (UserDto)SecurityUtils.getSubject().getPrincipal();
        return userEkilReviewRepository.existsByUserAndReview(userDto.getUid(), rid);
    }

}
