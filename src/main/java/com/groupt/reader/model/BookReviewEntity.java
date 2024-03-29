package com.groupt.reader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreType
@Entity
@Table(name = "boot_reviews")
public class BookReviewEntity implements Serializable {
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
    private Boolean disabled = false;

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

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
