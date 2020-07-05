package com.groupt.reader.market;

import com.groupt.reader.model.BookEntity;
import com.groupt.reader.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MarketController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/market")
    public Object get() {
        List<BookEntity> books = bookRepository.findAll();
        Map<String, Goods> dic = new HashMap<>();
        for(BookEntity book: books) {
            if(book.getDetail() == null) continue;
            Good b = new Good(book);
            if(!dic.containsKey(book.getDetail())) {
                Goods g = new Goods();
                g.setType(book.getDetail());
                dic.put(book.getDetail(), g);
            }
            dic.get(book.getDetail()).add(b);
        }
        List<Object> goodsList = new ArrayList<>();
        List<Type> typeList = new ArrayList<>();
        for(Map.Entry<String, Goods> entry: dic.entrySet()) {
            HashMap<String, Object> t = new HashMap<>();
            t.put("goods", entry.getValue());
            goodsList.add(t);
            typeList.add(new Type(entry.getKey(), null, null));
        }

        typeList.add(new Type());
        Map<String, Object> ret = new HashMap<>();
        ret.put("typeList", typeList);
        ret.put("goodsList", goodsList);
        return ret;
    }
}
