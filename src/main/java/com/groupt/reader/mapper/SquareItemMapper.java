package com.groupt.reader.mapper;

import com.groupt.reader.dto.SquareItemDto;
import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.CommentEntity;
import com.groupt.reader.model.UserEntity;

public class SquareItemMapper {
    public static SquareItemDto BookReviewToSquareItemDto(BookReviewEntity bookReviewEntity) {
        SquareItemDto squareItemDto = new SquareItemDto();
        squareItemDto.setContent(bookReviewEntity.getContent());
        squareItemDto.setTitle(bookReviewEntity.getTitle());
        UserEntity userEntity = bookReviewEntity.getAuthor();
        squareItemDto.setUserId(userEntity.getUid().toString());
        squareItemDto.setUserName(userEntity.getUname());
        squareItemDto.setUserResume(userEntity.getResume());
        return squareItemDto;
    }
}
