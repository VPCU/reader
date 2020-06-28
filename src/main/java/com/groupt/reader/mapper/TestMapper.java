package com.groupt.reader.mapper;

import com.groupt.reader.dto.TestDto;
import com.groupt.reader.model.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface TestMapper {

    @Mapping(target = "status", ignore = true)
    TestDto testToTestDto(Test test);
}
