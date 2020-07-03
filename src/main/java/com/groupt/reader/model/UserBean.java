package com.groupt.reader.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="user")
public class UserBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int uid;
    private String uname;
    private String password;
    private String phone;
    private String email;
    private int isadmin;

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getIsadmin() {
        return isadmin;
    }
    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }

}
