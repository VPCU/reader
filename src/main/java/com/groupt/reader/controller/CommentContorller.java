package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.NewCommentDto;
import com.groupt.reader.service.CommentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentContorller {
    @Autowired
    CommentService commentService;

    @RequiresAuthentication
    @GetMapping("/comments/all")
    public Object getAllCommentsByRid(@RequestParam Long rid) {
        return commentService.getAllCommentsByRid(rid);
    }

    @RequiresRoles("reader")
    @RequiresPermissions("new:comment")
    @PostMapping("/comments/new")
    public Object newComment(@RequestBody NewCommentDto commentDto) {
        boolean succ = commentService.newComments(commentDto.getReviewId(), commentDto.getFtCmtId(), commentDto.getContent());
        if(succ) return Json.succ("评论成功");
        else return Json.fail("评论失败。请检查书评是否存在。");
    }
}
