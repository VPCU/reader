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
        Goods goods = new Goods();
        for(BookEntity book: books) {
            Good b = new Good(book);
            goods.add(b);
        }
        GoodsList goodsList = new GoodsList();
        goodsList.add(goods);
        List<Type> typeList = new ArrayList<>();
        typeList.add(new Type());
        Map<String, Object> ret = new HashMap<>();
        ret.put("typeList", typeList);
        ret.put("goodsList", goodsList);
        return ret;
    }
}
