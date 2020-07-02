package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.NewBookReviewDto;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.service.BookReviewService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookReviewController {

    @Autowired
    private BookReviewService bookReviewService;

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @RequiresRoles("reader")
    @RequiresPermissions("new:review")
    @PostMapping("/reviews/new")
    public Object newReview(@RequestBody NewBookReviewDto bookReviewDto) {
        if(StringUtils.isEmpty(bookReviewDto.getTitle())) {
            return Json.fail("请输入标题");
        }
        boolean success = bookReviewService.newBookReview(bookReviewDto);
        if(success) return Json.succ("发布成功");
        else return Json.fail("发布失败");
    }

    @RequiresAuthentication
    @GetMapping("/reviews/all")
    public Object getSquareReviews(){
        return bookReviewService.getAllBookReviews();
    }

    @RequiresAuthentication
    @GetMapping("/reviews/bylimit")
    public Object getSquareReviewsByOffset(@RequestParam int offset, @RequestParam int limit, @RequestParam boolean desc){
        return bookReviewService.getBookReviews(offset, limit, desc);
    }

    @RequiresRoles("reader")
    @GetMapping("/reviews/my")
    public Object getMyReviews() {
        return bookReviewService.getSelfBookReviews();
    }
}
