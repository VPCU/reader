package com.groupt.reader.mapper;

import com.groupt.reader.dto.TestDto;
import com.groupt.reader.model.TestEntity;

public class MyTestMapper {
    public static TestDto testToTestDto(TestEntity test) {
        TestDto testDto = new TestDto();
        testDto.setCount(test.getCount());
        return testDto;
    }
}
