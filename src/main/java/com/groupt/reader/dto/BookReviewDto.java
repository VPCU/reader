package com.groupt.reader.dto;

import com.groupt.reader.model.BookEntity;
import com.groupt.reader.model.UserEntity;

import javax.persistence.Column;
import java.util.Date;


public class BookReviewDto {
    private Long rid;
    private Date createTime;
    private String content;
    private String title;
    private Long userId;
    private String username;
    private String userNick;
    private String userResume;
    private String userImgSrc;
    private Long bookId;
    private String bookAuthor;
    private String bookName;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserResume() {
        return userResume;
    }

    public void setUserResume(String userResume) {
        this.userResume = userResume;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserImgSrc() {
        return userImgSrc;
    }

    public void setUserImgSrc(String userImgSrc) {
        this.userImgSrc = userImgSrc;
    }
}
