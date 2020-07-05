package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.MyUserMapper;
import com.groupt.reader.model.Friends;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.FriendsRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friends")
public class FriendsController {
    @Autowired
    FriendsRepository friendsRepository;
    @Autowired
    UserRepository userRepository;

    @RequiresRoles("reader")
    @GetMapping("/add")
    public Json addFriend(@RequestParam Long uid) {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        Optional<UserEntity> _user = userRepository.findById(uid);
        if(!_user.isPresent()) return Json.fail("uid不合法");
        if(MyUserMapper.userToUserDto(_user.get()).getRoles().contains("admin"))
            return Json.fail("uid不合法");
        if(friendsRepository.existsByUser1AndUser2(userDto.getUid(), uid))
            return Json.succ("已经是好友");
        Friends friends = new Friends();
        friends.setUser1(userDto.getUid());
        friends.setUser2(uid);
        friendsRepository.save(friends);
        return Json.succ();
    }

    @RequiresRoles("reader")
    @GetMapping("/del")
    public Json delFriend(@RequestParam Long uid) {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        if(!friendsRepository.existsByUser1AndUser2(userDto.getUid(), uid)) return Json.fail("uid不合法");
        friendsRepository.deleteAllByUser1AndUser2(userDto.getUid(), uid);
        return Json.succ();
    }

    @RequiresRoles("reader")
    @GetMapping("/all")
    public Object getAllFriends() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        List<UserEntity> users = userRepository.getAllFriends(userDto.getUid());
        List<UserDto> friends = new ArrayList<>();
        for(UserEntity user: users) {
            UserDto u = MyUserMapper.userToUserDto(user);
            u.setRoles(null);
            u.setPerms(null);
            if(!user.getDisabled()) friends.add(u);
        }
        return friends;
    }

    @RequiresRoles("reader")
    @GetMapping("/find")
    public Object findFriend(@RequestParam Long uid) {
        UserEntity user = userRepository.findById(uid).get();
        return MyUserMapper.userToUserDto(user);
    }

}
