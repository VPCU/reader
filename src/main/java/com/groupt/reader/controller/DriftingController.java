package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.model.DriftingEntity;
import com.groupt.reader.service.DriftingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DriftingController {
    @Autowired
    private DriftingService driftingService;

    @RequiresRoles("reader")
    @RequiresPermissions("new:drifting")
    @PostMapping("/drifting/new")
    public Json createDrift(@RequestBody Map<String, Object> payload) {
        String name = (String)payload.get("bookName");
        String author = (String)payload.get("author");
        String isbn = (String)payload.get("isbn");
        String place = (String)payload.get("position");
        String guardian = (String)payload.get("guardian");
        if(StringUtils.isEmpty(name)) return Json.fail("书籍名称不能为空。");
        if(StringUtils.isEmpty(author)) return Json.fail("书籍作者不能为空");
        if(StringUtils.isEmpty(place)) return Json.fail("寄送处不能为空");
        Long driId = driftingService.newDrifting(name, author, isbn, place, guardian);
        return Json.succ("发起成功").data(driId);
    }

    @RequiresRoles("reader")
    @GetMapping("/drifting/all")
    public List<DriftingEntity> getUsersAllDrifting() {
        return driftingService.getSelfDrifting();
    }
}
