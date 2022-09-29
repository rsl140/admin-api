package com.serve.api.user.service;


import com.serve.api.comm.utils.CommUtils;
import com.serve.api.user.entity.UserSecurity;
import com.serve.api.user.repository.UserRepository;
import com.serve.api.user.repository.UserSecurityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class UserSecurityService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSecurityRepository userSecurityRepository;


    public boolean loginByPassword(int userId, String password) {
        UserSecurity userSecurity = userSecurityRepository.findByUserId(userId);
        String mixPassword = userSecurity.getMixPassword();
        if (StringUtils.isEmpty(mixPassword) || !mixPassword.equals(DigestUtils.md5DigestAsHex((password + userSecurity.getSalt()).getBytes(StandardCharsets.UTF_8)))) {
            return false;
        }
        userSecurityRepository.save(userSecurity);
        return true;
    }

    public UserSecurity setUserPassword(int userId, String password) {
        UserSecurity userSecurity = getUserSecurityAndCreateIfNotExist(userId);
        userSecurity = setUserPassword(userSecurity, password);
        return userSecurity;
    }

    public UserSecurity setUserPassword(UserSecurity userSecurity, String password) {
        //生成salt,生成mixPassword
        String salt = CommUtils.getRandom620(6);
        userSecurity.setSalt(salt);
        if (!StringUtils.isEmpty(password)) {
            String mixPassword = DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
            userSecurity.setMixPassword(mixPassword);
        }
        userSecurity = userSecurityRepository.save(userSecurity);
        return userSecurity;
    }

    public UserSecurity getUserSecurityAndCreateIfNotExist(int userId) {
        UserSecurity userSecurity = userSecurityRepository.findByUserId(userId);
        if (userSecurity == null) {
            userSecurity = new UserSecurity();
            userSecurity.setUserId(userId);
        }
        userSecurity = userSecurityRepository.save(userSecurity);
        return userSecurity;
    }


}
