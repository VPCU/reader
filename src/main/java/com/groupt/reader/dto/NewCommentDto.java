package com.groupt.reader.dto;

public class NewCommentDto {
    Long reviewId;
    Long ftCmtId;
    String content;

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
}
