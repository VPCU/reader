package com.groupt.reader.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boot_reviews")
public class BookReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity author;
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity book;
    private Date createTime;
    @Lob
    private String content;
    private String title;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
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
}
