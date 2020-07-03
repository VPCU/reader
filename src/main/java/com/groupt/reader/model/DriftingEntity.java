package com.groupt.reader.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "drifting")
public class DriftingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driId;
    private Long uid;
    private Long curUid;
    private String bookName;
    private String author;
    private String isbn;
    private String curPosition;
    private String curState;
    private Boolean available;
    private String guard;
    private String imgSrc;
    private Date createTime;
    @Lob
    private String events;

    public Long getDriId() {
        return driId;
    }

    public void setDriId(Long driId) {
        this.driId = driId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCurPosition() {
        return curPosition;
    }

    public void setCurPosition(String curPosition) {
        this.curPosition = curPosition;
    }

    public String getCurState() {
        return curState;
    }

    public void setCurState(String curState) {
        this.curState = curState;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public Long getCurUid() {
        return curUid;
    }

    public void setCurUid(Long curUid) {
        this.curUid = curUid;
    }

}
