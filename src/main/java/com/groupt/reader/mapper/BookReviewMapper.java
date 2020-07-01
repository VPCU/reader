package com.groupt.reader.mapper;

import com.groupt.reader.dto.BookReviewDto;
import com.groupt.reader.model.BookEntity;
import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.UserEntity;

public class BookReviewMapper {
    public static BookReviewDto BookReviewToBookReviewDto (BookReviewEntity bookReview){
        BookReviewDto bookReviewDto = new BookReviewDto();
        BookEntity book = bookReview.getBook();
        UserEntity user = bookReview.getAuthor();
        bookReviewDto.setRid(bookReview.getRid());
        bookReviewDto.setCreateTime(bookReview.getCreateTime());
        bookReviewDto.setTitle(bookReview.getTitle());
        bookReviewDto.setContent(bookReview.getContent());
        bookReviewDto.setBookId(book.getBid());
        bookReviewDto.setBookAuthor(book.getAuthor());
        bookReviewDto.setBookName(book.getName());
        bookReviewDto.setUserId(user.getUid());
        bookReviewDto.setUsername(user.getUname());
        bookReviewDto.setUserNick(user.getNick());
        bookReviewDto.setUserResume(user.getResume());
        return bookReviewDto;
    }
}
