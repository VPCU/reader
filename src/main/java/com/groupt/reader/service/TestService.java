package com.groupt.reader.service;

import com.groupt.reader.dto.TestDto;
import com.groupt.reader.mapper.TestMapper;
import com.groupt.reader.model.Test;
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
        Optional<Test> t = testRepository.findById(1L);
        if(t.isPresent()) {
            Test testEntity = t.get();
            Integer result = testEntity.getCount();
            testEntity.setCount(result + 1);
            return testMapper.testToTestDto(testRepository.save(testEntity));
        }
        throw new Exception("Database Exception");
    }

    public void init() {
        Test t = new Test();
        t.setId(1L);
        t.setCount(0);
        testRepository.save(t);
    }
}
