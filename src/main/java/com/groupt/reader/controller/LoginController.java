package com.groupt.reader.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groupt.reader.model.*;

import com.groupt.reader.service.*;

@Controller
@RequestMapping("/upc")
public class LoginController {

    @Autowired
    private LoginService loginClservice;
    @RequestMapping("/user/{uname}/{password}")
    public String getLoginCl(@RequestParam("uname") String uname,@RequestParam("password") String password) {

        System.out.println(uname);
        System.out.println(password);

        boolean b=true;

        String res=loginClservice.Chick(uname, password);
        System.out.println(res);
        if(!res.equals("success")) b=false;


        if(b) {

            return "wel";
        }
        else {

            return "login";
        }

    }
}
