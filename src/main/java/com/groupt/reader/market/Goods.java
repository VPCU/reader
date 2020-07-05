package com.groupt.reader.market;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Goods implements Serializable {
    String type;
    List<Good> list = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Good> getList() {
        return list;
    }

    public void setList(List<Good> list) {
        this.list = list;
    }

    public void add(Good book) {
        list.add(book);
    }
}
