package com.groupt.reader.controller;

import com.groupt.reader.repository.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SquareController {
    @Autowired
    private BookReviewRepository bookReviewRepository;

    @RequestMapping("/square/reviews")
    public Object getSquareReviews(){
        return bookReviewRepository.findAll();
    }

}
