package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.NewBookReviewDto;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.service.BookReviewService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookReviewController {

    @Autowired
    private BookReviewService bookReviewService;

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @RequiresRoles("reader")
    @PostMapping("/newreview")
    public Object newReview(@RequestBody NewBookReviewDto bookReviewDto) {
        if(StringUtils.isEmpty(bookReviewDto.getTitle())) {
            return Json.fail("请输入标题");
        }
        boolean success = bookReviewService.newBookReview(bookReviewDto);
        if(success) return Json.succ("发布成功");
        else return Json.fail("发布失败");
    }

    @RequestMapping("/square/reviews")
    public Object getSquareReviews(){
        return bookReviewService.getAllBookReviews();
    }
}
