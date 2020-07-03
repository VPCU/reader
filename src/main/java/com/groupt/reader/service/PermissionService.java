package com.groupt.reader.service;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.MyUserMapper;
import com.groupt.reader.mapper.UserMapper;
import com.groupt.reader.model.PermEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.PermRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    private PermRepository permRepository;
    @Autowired
    private UserRepository userRepository;

    public void newPermission(Long id, String name, String val) {
        PermEntity p = new PermEntity();
        if(id != null) p.setPid(id);
        p.setPname(name);
        p.setPval(val);
        permRepository.save(p);
    }

    public void addPermission(UserEntity user, String val) {
        UserDto dto = MyUserMapper.userToUserDto(user);
        if(dto.getPerms().contains(val)) return;
        user.getPerms().add(permRepository.findByPval(val));
        userRepository.save(user);
    }

    public void removePermission(UserEntity user, String val) {
        UserDto dto = MyUserMapper.userToUserDto(user);
        if(!dto.getPerms().contains(val)) return;
        PermEntity perm = permRepository.findByPval(val);
        user.getPerms().remove(perm);
        userRepository.save(user);
    }
}
