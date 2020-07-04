package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.DriftingEntity;
import com.groupt.reader.repository.DriftingRepository;
import com.groupt.reader.service.DriftingService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DriftingController {
    @Autowired
    private DriftingService driftingService;

    @Autowired
    private DriftingRepository driftingRepository;

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

    @RequiresAuthentication
    @GetMapping("/drifting/id/{id}")
    public Object getDriftingById(@PathVariable(name = "id") int id) {
        return driftingService.getDriftingById((long)id);
    }

    @RequiresRoles("reader")
    @RequiresPermissions("new:drifting")
    @PostMapping("/drifting/edit")
    public Object setDrifting(@RequestBody Map<String, Object> payload) {
        if(payload.get("driftId") == null) return Json.fail("id 不能为空");
        int driftId = (int)payload.get("driftId");
        String bookName = (String)payload.get("bookName");
        String author = (String)payload.get("author");
        String isbn = (String)payload.get("isbn");
        String position = (String)payload.get("position");
        String guardian = (String)payload.get("guardian");
        if(!driftingRepository.findById((long)driftId).isPresent()) return Json.fail("错误的id");
        DriftingEntity drift = driftingRepository.findById((long)driftId).get();
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        if(!userDto.getUid().equals(drift.getUid())) return Json.fail("没有权限修改");
        if(bookName != null) drift.setBookName(bookName);
        if(author != null) drift.setAuthor(author);
        if(isbn != null) drift.setIsbn(isbn);
        if(position != null) drift.setCurPosition(position);
        if(guardian != null) drift.setGuard(guardian);
        driftingRepository.save(drift);
        return Json.succ();
    }

    @RequiresAuthentication
    @RequiresPermissions("new:drifting")
    @GetMapping("/drifting/borrow")
    public Json borrow(@RequestParam Long driftId) {
        if(driftingService.borrowById(driftId)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }

    @RequiresAuthentication
    @RequiresPermissions("new:drifting")
    @GetMapping("/drifting/return")
    public Json giveBookBack(@RequestParam Long driftId) {
        if(driftingService.returnById(driftId)) {
            return Json.succ();
        } else {
            return Json.fail();
        }
    }

    @RequiresAuthentication
    @GetMapping("/drifting/bylimit")
    public Object getSquareReviewsByOffset(@RequestParam int cursor, @RequestParam int limit, @RequestParam boolean desc){
        return driftingService.getDrifting(cursor, limit, desc);
    }
}
