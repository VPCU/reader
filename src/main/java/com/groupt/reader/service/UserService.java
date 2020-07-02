package com.groupt.reader.service;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.MyUserMapper;
import com.groupt.reader.model.RoleEntity;
import com.groupt.reader.model.UserEntity;
import com.groupt.reader.repository.RoleRepository;
import com.groupt.reader.repository.UserRepository;
import com.groupt.reader.util.ByteArrayToString;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    //@Autowired
    //private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final RandomNumberGenerator randomNumberGenerator;


    UserService() {
        randomNumberGenerator = new SecureRandomNumberGenerator();
    }

    private String[] hashPassword(String raw) {
        ByteSource salt  = randomNumberGenerator.nextBytes(32);

        byte[] byteTabSalt  = salt.getBytes();

        String strSalt = ByteArrayToString.bytesToHex(byteTabSalt);

        return new String[] {new Sha256Hash(raw, strSalt, 1024).toBase64(), strSalt};
    }

    public UserDto findUserByName(String name) {
        UserEntity u = userRepository.findByUname(name);
        return MyUserMapper.userToUserDto(u);
    }

    public UserDto readerSignUp(String userName, String nick, String rawPassword, String email, String phone) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUname(userName);
        userEntity.setNick(nick);
        userEntity.setEmail(email);
        userEntity.setPhone(phone);
        String[] r = hashPassword(rawPassword);
        userEntity.setPwd(r[0]);
        userEntity.setSalt(r[1]);
        userEntity.setCreated(new Date());
        userEntity.setUpdated(new Date());
        userEntity.getRoles().add(roleRepository.findByRval("reader"));
        userEntity.getRoles().add(roleRepository.findByRval("new:review"));
        userEntity.getRoles().add(roleRepository.findByRval("new:comment"));
        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
        UserEntity user = userRepository.findByUname(userName);
        return MyUserMapper.userToUserDto(user);
    }

    public boolean userExists(String userName) {
        return userRepository.existsByUname(userName);
    }
}
