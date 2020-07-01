package com.groupt.reader.mapper;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.PermEntity;
import com.groupt.reader.model.RoleEntity;
import com.groupt.reader.model.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class MyUserMapper {
    public static UserDto userToUserDto(UserEntity user) {
        UserDto userDto = new UserDto();
        if(user == null) return null;
        userDto.setCreated(user.getCreated());
        userDto.setNick(user.getNick());
        userDto.setPwd(user.getPwd());
        userDto.setUid(user.getUid());
        userDto.setSalt(user.getSalt());
        userDto.setUname(user.getUname());
        userDto.setUpdated(user.getUpdated());

        Set<PermEntity> perms = user.getPerms();
        Set<RoleEntity> roles = user.getRoles();
        Set<String> p = new HashSet<String>();
        Set<String> r = new HashSet<String>();
        for(PermEntity it : perms) {
            p.add(it.getPval());
        }
        for(RoleEntity it : roles) {
            r.add(it.getRval());
        }
        userDto.setPerms(p);
        userDto.setRoles(r);

        return userDto;
    }
}
