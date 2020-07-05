package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.ReportEntity;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.repository.ReportRepository;
import com.groupt.reader.repository.UserRepository;
import com.groupt.reader.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookReviewRepository bookReviewRepository;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    AdminService adminService;

    @RequiresRoles("reader")
    @PostMapping("/review")
    public Object reportReview(@RequestBody Map<String, Object> payload) {
        if(payload.get("reviewId") == null) return Json.fail("id不合法");
        int reviewId = (int)payload.get("reviewId");
        String content = (String)payload.get("content");
        if(!bookReviewRepository.existsById((long)reviewId))
            return Json.fail("id不合法");
        if(StringUtils.isEmpty(content)) return Json.fail("content不能为空");
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setReporter(userDto.getUid());
        reportEntity.setContent(content);
        reportEntity.setReviewId((long)reviewId);
        reportRepository.save(reportEntity);
        return Json.succ();
    }

    @RequiresRoles("admin")
    @GetMapping("/admin/reviews")
    public Object getAllReports() {
        return adminService.getAllReports();
    }

    @RequiresRoles("admin")
    @PostMapping("/admin/reviews")
    public Object setDone(@RequestBody Map<String, Object> payload) {
        if(payload.get("reportId") == null) return Json.fail("reportId不合法");
        int reportId = (int)payload.get("reportId");
        String status = (String)payload.get("status");
        String log = (String)payload.get("log");
        if(!reportRepository.existsById((long)reportId))
            return Json.fail("id不合法");
        if(StringUtils.isEmpty(status)) return Json.fail("stauts不能为空");
        ReportEntity report = reportRepository.findById((long)reportId).get();
        if(report.getDone()) return Json.fail("已被处理过");
        report.setDone(true);
        report.setLog(log);
        report.setStatus(status);
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        report.setAdminId(userDto.getUid());
        reportRepository.save(report);
        return Json.succ();
    }
}
