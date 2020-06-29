package com.groupt.reader.service;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.UserMapper;
import com.groupt.reader.model.PermEntity;
import com.groupt.reader.model.RoleEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    public UserDto findUserByName(String name) {
        UserEntity u = userRepository.findByUname(name);
        Set<PermEntity> perms = u.getPerms();
        Set<RoleEntity> roles = u.getRoles();
        Set<String> p = new HashSet<String>();
        Set<String> r = new HashSet<String>();
        for(PermEntity it : perms) {
            p.add(it.getPval());
        }
        for(RoleEntity it : roles) {
            r.add(it.getRval());
        }
        UserDto userDto = userMapper.userToUserDto(u);
        userDto.setPerms(p);
        userDto.setRoles(r);
        return userDto;
    }
}
