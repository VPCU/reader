package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.service.AdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequiresRoles("admin")
    @GetMapping("/admin/getallusers")
    public Object getAllUsers() {
        return adminService.getAllUsersList();
    }

    @RequiresRoles("admin")
    @RequiresPermissions("disable:reader")
    @GetMapping("/admin/setdisabled")
    public Object setDisabled(@RequestParam Long id, @RequestParam Boolean disabled) {
        if(adminService.setDisabled(id, disabled)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }

}
