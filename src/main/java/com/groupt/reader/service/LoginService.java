package com.groupt.reader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupt.reader.Dao.*;
import com.groupt.reader.model.*;

@Service
public class LoginService {

    @Autowired
    private UserDao userdao;//与dao层进行交互


    public String Chick(String uname,String password) {

        UserBean b=userdao.findByUnameAndPassword(uname, password);

        if(b==null) {

            return "null";
        }

        String aString=b.getUname();

        String bString=b.getPassword();

        if(aString.equals(uname)&&bString.equals(password)) {

            return "success";
        }else {

            return "error";
        }


    }
}
