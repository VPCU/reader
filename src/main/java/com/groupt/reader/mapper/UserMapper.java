package com.groupt.reader.mapper;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "perms", ignore = true)
    UserDto userToUserDto(UserEntity user);
    //UserEntity userDtoToUser(UserDto userDto);
}
