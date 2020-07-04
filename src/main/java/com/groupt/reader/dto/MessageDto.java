package com.groupt.reader.dto;

import com.groupt.reader.mapper.MyUserMapper;
import com.groupt.reader.model.MessageEntity;
import com.groupt.reader.model.UserEntity;

import java.io.Serializable;

public class MessageDto implements Serializable {
    private MessageEntity message;
    private UserDto user;

    public MessageDto(MessageEntity msg, UserEntity userEntity) {
        user = MyUserMapper.userToUserDto(userEntity);
        user.setPerms(null);
        user.setRoles(null);
        user.setUpdated(null);
        message = msg;
    }

    public MessageEntity getMessage() {
        return message;
    }

    public void setMessage(MessageEntity message) {
        this.message = message;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
