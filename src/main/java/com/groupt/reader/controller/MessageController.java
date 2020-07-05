package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.MessageEntity;
import com.groupt.reader.repository.MessageRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @RequiresAuthentication
    @GetMapping("/message/update")
    public Json updateUser() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        if(userRepository.update(userDto.getUid(), new Date()) < 1) return Json.fail();
        return Json.succ().data(userRepository.getUpdatedByUid(userDto.getUid()));
    }

    @RequiresAuthentication
    @GetMapping("/message/getupdated")
    public Json getUpdated() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        return Json.succ().data(userRepository.getUpdatedByUid(userDto.getUid()));
    }

    @RequiresAuthentication
    @RequiresPermissions("new:message")
    @PostMapping("/message/send")
    public Json sendAMessage(@RequestBody Map<String, Object> payload) {
        if(payload.get("receiverId") == null) return Json.fail("非法的接受者id");
        int receiverId = Integer.parseInt((String)payload.get("receiverId"));
        String content = (String)payload.get("content");
        MessageEntity msg = new MessageEntity();
        if(!userRepository.existsById((long)receiverId)) return Json.fail("非法的接受者id");
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        msg.setUid(userDto.getUid());
        msg.setRvId((long)receiverId);
        msg.setMsg(content);
        msg.setTime(new Date());
        messageRepository.save(msg);
        return Json.succ();
    }

    @RequiresAuthentication
    @GetMapping("/message/all")
    public Object getAllReceived() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        return messageRepository.getMessageDtoByRvId(userDto.getUid());
    }

    @RequiresAuthentication
    @GetMapping("/message/allsent")
    public Object getAllSent() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        return messageRepository.getMessageDtoByUid(userDto.getUid());
    }
}
