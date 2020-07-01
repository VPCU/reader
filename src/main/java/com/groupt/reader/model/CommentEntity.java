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
}
