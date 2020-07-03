package com.groupt.reader.Dao;

import com.groupt.reader.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserBean, Integer> {
    UserBean findByUnameAndPassword(String uname,String password);
}

