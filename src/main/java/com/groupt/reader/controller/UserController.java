package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import com.groupt.reader.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @PostMapping("/login")
    /*public String login(@RequestBody String body) {
        JSONObject json = JSON.parseObject(body);
        String username = json.getString("username");
        String password = json.getString("password");
    }*/
    public Json login(@RequestBody Map<String, Object> payload) {
        String username = (String)payload.get("username");
        String password = (String)payload.get("password");
        if(StringUtils.isEmpty(username)) {
            return Json.fail("用户名不能为空");
        }
        if(StringUtils.isEmpty(password)) {
            return Json.fail("密码不能为空");
        }
        Subject currentUser = SecurityUtils.getSubject();
        try{
            currentUser.login( new UsernamePasswordToken(username, password));
            UserDto user = (UserDto) currentUser.getPrincipal();
            if (user == null) throw new AuthenticationException();
            return Json.succ()
                    .data("uid", user.getUid())
                    .data("nick", user.getNick())
                    .data("roles", user.getRoles())
                    .data("perms", user.getPerms());
        } catch ( UnknownAccountException uae ) {
            return Json.fail("用户帐号或密码不正确");
        } catch ( IncorrectCredentialsException ice ) {
            return Json.fail("用户帐号或密码不正确");
        } catch ( LockedAccountException lae ) {
            return Json.fail("用户帐号被锁定不可用");
        } catch ( AuthenticationException ae ) {
            return Json.fail("登录失败："+ae.getMessage());
        }
    }
}
