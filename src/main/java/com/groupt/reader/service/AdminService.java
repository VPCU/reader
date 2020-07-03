package com.groupt.reader.service;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.MyUserMapper;
import com.groupt.reader.mapper.UserMapper;
import com.groupt.reader.model.ReportEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.BookReviewRepository;
import com.groupt.reader.repository.ReportRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PermissionService permissionService;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private BookReviewRepository bookReviewRepository;

    public List<UserDto> getAllUsersList() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDto> ret = new ArrayList<>();
        for(UserEntity user: users) {
            UserDto u = MyUserMapper.userToUserDto(user);
            if(!u.getRoles().contains("admin")) {
                u.setPwd("");
                u.setSalt("");
                ret.add(u);
            }
        }
        return ret;
    }

    public boolean setDisabled(Long uid, boolean disabled) {
        Optional<UserEntity> _user = userRepository.findById(uid);
        if(!_user.isPresent()) return false;
        UserEntity user = _user.get();
        UserDto u = MyUserMapper.userToUserDto(user);
        if(u.getRoles().contains("admin")) throw new UnauthorizedException("管理员没有对应的权限");
        user.setDisabled(disabled);
        userRepository.saveAndFlush(user);
        return true;
    }

    public boolean adminRemoveUsersPermission(Long uid, String val) {
        UserDto admin = (UserDto) SecurityUtils.getSubject().getPrincipal();
        if(!admin.getPerms().contains(val))
            throw new UnauthorizedException("管理员没有对应的权限");
        Optional<UserEntity> _user = userRepository.findById(uid);
        if(!_user.isPresent()) return false;
        UserEntity user = _user.get();
        if(MyUserMapper.userToUserDto(user).getRoles().contains("admin"))
            throw new UnauthorizedException("管理员没有对应的权限");
        permissionService.removePermission(user, val);
        return true;
    }

    public boolean adminAddUsersPermission(Long uid, String val) {
        UserDto admin = (UserDto) SecurityUtils.getSubject().getPrincipal();
        if(!admin.getPerms().contains(val)) throw new UnauthorizedException("管理员没有对应的权限");
        Optional<UserEntity> _user = userRepository.findById(uid);
        if(!_user.isPresent()) return false;
        UserEntity user = _user.get();
        if(MyUserMapper.userToUserDto(user).getRoles().contains("admin"))
            throw new UnauthorizedException("管理员没有对应的权限");
        permissionService.addPermission(user, val);
        return true;
    }

    public List<Map<String, Object>> getAllReports() {
        List<ReportEntity> reports = reportRepository.getAllByDone(false);
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> ret = new ArrayList<>();
        for(ReportEntity report: reports) {
            result.put("reportId", report.getRptId());
            result.put("reporter", userRepository.findById(report.getReporter()).get());
            result.put("review", bookReviewRepository.findById(report.getReviewId()).get());
            result.put("content", report.getContent());
            ret.add(result);
        }
        return ret;
    }
}
