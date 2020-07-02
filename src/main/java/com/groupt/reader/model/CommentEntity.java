package com.groupt.reader.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmtId;
    @ManyToOne(fetch = FetchType.LAZY)
    private BookReviewEntity review;
    private Date createTime;
    private Long reviewId;
    private Long ftCmtId;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity creator;
    @Lob
    private String content;

    public Long getCmtId() {
        return cmtId;
    }

    public void setCmtId(Long cmtId) {
        this.cmtId = cmtId;
    }

    public BookReviewEntity getReview() {
        return review;
    }

    public void setReview(BookReviewEntity review) {
        this.review = review;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getFtCmtId() {
        return ftCmtId;
    }

    public void setFtCmtId(Long ftCmtId) {
        this.ftCmtId = ftCmtId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }
}
