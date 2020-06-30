package com.groupt.reader.mapper;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.UserEntity;

public class MyUserMapper {
    public static UserDto userToUserDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setCreated(user.getCreated());
        userDto.setNick(user.getNick());
        userDto.setPwd(user.getPwd());
        userDto.setUid(user.getUid());
        userDto.setSalt(user.getSalt());
        userDto.setUname(user.getUname());
        userDto.setUpdated(user.getUpdated());
        return userDto;
    }
}
