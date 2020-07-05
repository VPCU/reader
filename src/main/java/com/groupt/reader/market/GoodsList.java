package com.groupt.reader.market;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoodsList implements Serializable {
    List<Object> goodsList = new ArrayList<>();

    public List<Object> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Object> goodsList) {
        this.goodsList = goodsList;
    }

    public void add(Goods goods) {
        HashMap<String, Goods> map = new HashMap<>();
        map.put("goods", goods);
        goodsList.add(map);
    }
}
