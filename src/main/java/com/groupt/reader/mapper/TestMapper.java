package com.groupt.reader.mapper;

import com.groupt.reader.dto.TestDto;
import com.groupt.reader.model.TestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface TestMapper {

    @Mapping(target = "status", ignore = true)
    TestDto testToTestDto(TestEntity test);
}
