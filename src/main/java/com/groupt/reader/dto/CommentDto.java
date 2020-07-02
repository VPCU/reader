package com.groupt.reader.dto;

import java.util.Date;

public class CommentDto {
    Long id;
    Long reviewId;
    Long creatorId;
    String creatorUserName;
    String creatorUserNick;
    String creatorUserResume;
    Date createTime;
    Long fatherCommentId;
    String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public String getCreatorUserNick() {
        return creatorUserNick;
    }

    public void setCreatorUserNick(String creatorUserNick) {
        this.creatorUserNick = creatorUserNick;
    }

    public String getCreatorUserResume() {
        return creatorUserResume;
    }

    public void setCreatorUserResume(String creatorUserResume) {
        this.creatorUserResume = creatorUserResume;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(Long fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
