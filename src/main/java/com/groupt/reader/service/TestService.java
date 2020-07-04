package com.groupt.reader.service;

import com.groupt.reader.dto.TestDto;
import com.groupt.reader.mapper.MyTestMapper;
import com.groupt.reader.mapper.TestMapper;
import com.groupt.reader.model.PermEntity;
import com.groupt.reader.model.RoleEntity;
import com.groupt.reader.model.TestEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.PermRepository;
import com.groupt.reader.repository.RoleRepository;
import com.groupt.reader.repository.TestRepository;
import com.groupt.reader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermRepository permRepository;

    @Autowired
    private PermissionService permissionService;

    //@Autowired
    //private TestMapper testMapper;

    public TestDto count() throws Exception {
        Optional<TestEntity> t = testRepository.findById(1L);
        if(t.isPresent()) {
            TestEntity testEntity = t.get();
            Integer result = testEntity.getCount();
            testEntity.setCount(result + 1);
            return MyTestMapper.testToTestDto(testRepository.save(testEntity));
        }
        throw new Exception("Database Exception");
    }

    public void init() {
        permissionService.newPermission(1L, "封禁帐号", "disable:reader");
        permissionService.newPermission(2L, "发布书评", "new:review");
        permissionService.newPermission(3L, "发布评论", "new:comment");
        permissionService.newPermission(4L, "发布漂流", "new:drifting");
        permissionService.newPermission(5L, "超级管理", "whosyourdaddy");
        permissionService.newPermission(6L, "查看帐号", "show:reader");
        permissionService.newPermission(7L, "解封帐号", "enable:reader");
        permissionService.newPermission(8L, "删除评论", "disable:comment");
        permissionService.newPermission(9L, "删除书评", "disable:review");
        permissionService.newPermission(10L, "授予权限", "addperm:reader");
        permissionService.newPermission(11L, "移除权限", "rmperm:reader");
        permissionService.newPermission(12L, "处理举报", "process:report");
        RoleEntity r = new RoleEntity();
        r.setRid(1L);
        r.setRname("管理员");
        r.setRval("admin");
        roleRepository.save(r);
        r.setRid(2L);
        r.setRname("读者");
        r.setRval("reader");
        roleRepository.save(r);

        TestEntity t = new TestEntity();
        t.setId(1L);
        t.setCount(0);
        testRepository.save(t);

        UserEntity u = new UserEntity();
        u.setUid(1L);
        u.setUname("admin001");
        u.setPwd("J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=");
        u.setSalt("wxKYXuTPST5SG0jMQzVPsg==");
        u.setNick("管理员1");
        u.getRoles().add(roleRepository.findByRval("admin"));
        userRepository.save(u);
        permissionService.addPermission(u, "disable:reader");
        permissionService.addPermission(u, "new:review");
        permissionService.addPermission(u, "new:comment");
        permissionService.addPermission(u, "new:drifting");
        permissionService.addPermission(u, "show:reader");
        permissionService.addPermission(u, "enable:reader");
        permissionService.addPermission(u, "disable:comment");
        permissionService.addPermission(u, "disable:review");
        permissionService.addPermission(u, "addperm:reader");
        permissionService.addPermission(u, "rmperm:reader");
        permissionService.addPermission(u, "process:report");

    }
}
