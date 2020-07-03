package com.groupt.reader.model;

import com.groupt.reader.repository.PermRepository;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;       // 用户id

    @Column(unique = true)
    private String uname;   // 登录名，不可改

    private String nick;    // 用户昵称，可改

    private String pwd;     // 已加密的登录密码

    private String salt;    // 加密盐值

    private Date created;   // 创建时间

    private Date updated;   // 修改时间

    private String email;

    private String phone;

    private String resume; //个人说明

    @Column(columnDefinition = "boolean default false")
    private Boolean disabled;

    @ManyToMany(targetEntity = RoleEntity.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(targetEntity = PermEntity.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<PermEntity> perms = new HashSet<>();

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<PermEntity> getPerms() {
        return perms;
    }

    public void setPerms(Set<PermEntity> perms) {
        this.perms = perms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
