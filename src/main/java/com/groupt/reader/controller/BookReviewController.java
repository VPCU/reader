package com.groupt.reader.controller;

import com.groupt.reader.dto.BookReviewDto;
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

    @RequiresRoles("reader")
    @GetMapping("/reviews/getlike")
    public boolean like(@RequestParam int rid) {
        return bookReviewService.likeByRid(rid);
    }

    @RequiresRoles("reader")
    @GetMapping("/reviews/setlike")
    public Json setLike(@RequestParam Long rid, boolean like) {
        if(bookReviewService.setSelfLikeReview(rid, like)) {
            return Json.succ("Ok.");
        } else {
            return Json.fail("Fail.");
        }
    }

    @RequiresRoles("reader")
    @GetMapping("/reviews/countlike")
    public int countLike() {
        return bookReviewService.countLike();
    }

    @RequiresRoles("reader")
    @GetMapping("/reviews/like")
    public Object getAllLike() {
        return bookReviewService.getLikes();
    }

    @RequiresAuthentication
    @GetMapping("/reviews/rid/{id}")
    public Object getReviewsById(@PathVariable(name = "id") int id) {
        BookReviewDto review = bookReviewService.getBookReviewById((long) id);
        if(review == null) return Json.fail("请求失败。");
        else return Json.succ().data(review);
    }


    @RequiresAuthentication
    @GetMapping("/reviews/countekil")
    public int countEkils(@RequestParam Long rid) {
        return bookReviewService.countEkil(rid);
    }

    @RequiresRoles("reader")
    @GetMapping("/reviews/getekil")
    public boolean getEkil(@RequestParam Long rid) {
        return bookReviewService.getEkil(rid);
    }

    @RequiresRoles("reader")
    @GetMapping("/reviews/setekil")
    public Json setEkil(@RequestParam Long rid, @RequestParam boolean ekil) {
        if(bookReviewService.setEkil(rid, ekil)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }
}
