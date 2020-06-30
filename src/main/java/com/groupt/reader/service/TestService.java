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
        RoleEntity r = new RoleEntity();
        r.setRid(1L);
        r.setRname("管理员");
        r.setRval("admin");
        PermEntity p = new PermEntity();
        p.setPid(1L);
        p.setPname("封禁帐号");
        p.setPval("disable users");
        u.getRoles().add(r);
        u.getPerms().add(p);
        roleRepository.save(r);
        permRepository.save(p);
        userRepository.save(u);
    }
}
