package com.groupt.reader.market;

import com.groupt.reader.model.BookEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Good implements Serializable {
    int id;
    String name;
    Integer price;
    List<String> img = new ArrayList<>();
    String explain;

    Good(BookEntity book) {
        id = book.getBid().intValue();
        name = book.getName();
        if(book.getLanguage() != null)
            price = (int)(Float.parseFloat(book.getLanguage()) * 100);
            img.add(book.getImage());
        explain = book.getDetail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
