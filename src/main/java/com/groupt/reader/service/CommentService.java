package com.groupt.reader.service;

import com.groupt.reader.dto.CommentDto;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.CommentMapper;
import com.groupt.reader.model.BookEntity;
import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.CommentEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.repository.CommentRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private BookReviewRepository bookReviewRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CommentDto> getAllCommentsByRid(Long rid) {
        List<CommentEntity> commentEntities = commentRepository.findAllByReviewId(rid);
        List<CommentDto> comments = new ArrayList<>();
        for(CommentEntity comment : commentEntities) {
            comments.add(CommentMapper.commentToCommentDto(comment));
        }
        return comments;
    }

    public boolean newComments(Long reviewId, Long ftCmtId, String content) {
        Optional<BookReviewEntity> bookReview = bookReviewRepository.findById(reviewId);
        if(!bookReview.isPresent()) return false;
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        UserEntity userEntity = userRepository.findByUname(userDto.getUname());
        CommentEntity comment = new CommentEntity();
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setCreator(userEntity);
        comment.setFtCmtId(ftCmtId);
        comment.setReview(bookReview.get());
        comment.setReviewId(reviewId);
        commentRepository.save(comment);
        return true;
    }
}
