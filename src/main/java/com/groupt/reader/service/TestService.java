package com.groupt.reader.service;

import com.groupt.reader.dto.TestDto;
import com.groupt.reader.mapper.TestMapper;
import com.groupt.reader.model.TestEntity;
import com.groupt.reader.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestMapper testMapper;

    public TestDto count() throws Exception {
        Optional<TestEntity> t = testRepository.findById(1L);
        if(t.isPresent()) {
            TestEntity testEntity = t.get();
            Integer result = testEntity.getCount();
            testEntity.setCount(result + 1);
            return testMapper.testToTestDto(testRepository.save(testEntity));
        }
        throw new Exception("Database Exception");
    }

    public void init() {
        TestEntity t = new TestEntity();
        t.setId(1L);
        t.setCount(0);
        testRepository.save(t);
    }
}
