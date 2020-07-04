package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.service.AdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiresRoles("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @RequiresPermissions("show:reader")
    @GetMapping("/admin/getallusers")
    public Object getAllUsers() {
        return adminService.getAllUsersList();
    }

    @RequiresPermissions("disable:reader")
    @GetMapping("/admin/disableuser")
    public Object setDisabled(@RequestParam Long id) {
        if(adminService.setDisabled(id, false)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }

    @RequiresPermissions("enable:reader")
    @GetMapping("/admin/enableuser")
    public Object setEnabled(@RequestParam Long id) {
        if(adminService.setDisabled(id, true)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }

    @RequiresPermissions("addperm:reader")
    @GetMapping("/admin/addpermission")
    public Object addPermission(@RequestParam Long id, @RequestParam String val) {
        if(adminService.adminAddUsersPermission(id, val)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }


    @RequiresPermissions("rmperm:reader")
    @GetMapping("/admin/removepermission")
    public Object rmPermission(@RequestParam Long id, @RequestParam String val) {
        if(adminService.adminRemoveUsersPermission(id, val)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }

    @GetMapping("/admin/disablereview")
    public Object setDisableReview(@RequestParam int rid, @RequestParam Boolean disable) {
        Optional<BookReviewEntity> _r = bookReviewRepository.findById((long) rid);
        if(!_r.isPresent()) return Json.fail("rid不合法");
        BookReviewEntity r = _r.get();
        r.setDisabled(disable);
        bookReviewRepository.save(r);
        return Json.succ();
    }

}
