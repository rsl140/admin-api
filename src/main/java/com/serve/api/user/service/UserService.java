package com.serve.api.user.service;


import com.serve.api.comm.config.JwtHelper;
import com.serve.api.comm.enums.EnableStatus;
import com.serve.api.comm.enums.ErrorCode;
import com.serve.api.comm.model.BusinessException;
import com.serve.api.user.entity.User;
import com.serve.api.user.enums.LoginType;
import com.serve.api.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Lazy
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSecurityService userSecurityService;

    public User loginByPassword(String mobile, String password) {
        List<User> userList = new ArrayList<>();
        User user = userRepository.findFirstByMobileAndStatus(mobile, EnableStatus.ENABLE);
        if (null != user) {
            userList.add(user);
        }
        userList.removeIf(user1 -> user1.getStatus() != EnableStatus.ENABLE);
        if (CollectionUtils.isEmpty(userList)) {
            throw BusinessException.instance(ErrorCode.USER_NOT_EXIST);
        }
        User loginUser = null;
        for (User checkUser : userList) {
            if (userSecurityService.loginByPassword(checkUser.getId(), password)) {
                loginUser = checkUser;
            }
        }
        if (null == loginUser) {
            throw BusinessException.instance(ErrorCode.NAME_PW_OR_ERROR);
        }
        return loginUser;
    }

    public User getById(int userId) {
        long timestart = System.currentTimeMillis();
        User user = userRepository.findById(userId).orElse(null);
        log.info("findById(userId) 耗时：{} 毫秒", System.currentTimeMillis() - timestart);
        if (user == null || EnableStatus.ENABLE != user.getStatus()) {
            throw BusinessException.instance(ErrorCode.USER_UNNORMAL);
        }
        return user;
    }

    public Map<String, Object> login(LoginType loginType, String mobile, String param) {
        String region = null;
        String userInviteCode = null;
        User user = null;
        if (LoginType.PASSWORD == loginType && !StringUtils.isEmpty(param)) {
            if (StringUtils.isEmpty(mobile)) {
                throw BusinessException.instance(ErrorCode.E_PARAM);
            }
            user = loginByPassword(mobile, param);
        } else if (StringUtils.isEmpty(param)) {
            throw BusinessException.instance(ErrorCode.E_PARAM);
        }
        Map<String, Object> map = new HashMap<>();
        String token = JwtHelper.createJWT(user.getId() + "");
        map.put("access_token", token);
        map.put("expires_in", JwtHelper.expirestime / 1000);
        map.put("scope", "read write");
        return map;
    }

//    public User registerByPhone(String phone, String password, String region) {
//        String lockKey = "registerByPhone-" + phone;
//        String lock = MemoryCache.getLock(lockKey);
//        synchronized (lock) {
//            return registerByPhone_(phone, password, region);
//        }
//    }
//
//    public synchronized User registerByPhone_(String phone, String password, String region) {
//        //参数验证
//        if (StringUtils.isEmpty(phone)) {
//            throw new BusinessException(ErrorCode.E_PARAM);
//        }
//        //是否已经存在
//        User user = userRepository.findFirstByMobileAndStatus(phone, EnableStatus.ENABLE);
//        if (user == null) {
////            throw new BusinessException(ErrorCode.USER_HAD_EXISTS);
//            user = createAndSaveUser(phone, region);
//        }
//        userSecurityService.setUserPassword(user.getId(), password);
//        return user;
//    }

//    private /*synchronized*/ User createAndSaveUser(String mobile, String region) {
//        User user = new User();
//        if (!StringUtils.isEmpty(mobile)) {
//            user.setMobile(mobile);
//        }
//        user = userRepository.save(user);
//        int userId = user.getId();
//        return userRepository.save(user);
//    }
}