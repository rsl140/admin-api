package com.serve.api.user.service;


import com.serve.api.comm.config.JwtHelper;
import com.serve.api.comm.enums.EnableStatus;
import com.serve.api.comm.enums.ErrorCode;
import com.serve.api.comm.model.BusinessException;
import com.serve.api.comm.service.MemoryCache;
import com.serve.api.comm.utils.SpringUtil;
import com.serve.api.user.entity.User;
import com.serve.api.user.enums.LoginType;
import com.serve.api.user.model.LoginBody;
import com.serve.api.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
@Lazy
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSecurityService userSecurityService;

    public User getById(int userId) {
        long timestart = System.currentTimeMillis();
        User user = userRepository.findById(userId).orElse(null);
        log.info("findById(userId) 耗时：{} 毫秒", System.currentTimeMillis() - timestart);
        if (user == null || EnableStatus.ENABLE != user.getStatus()) {
            throw BusinessException.instance(ErrorCode.USER_UNNORMAL);
        }
        return user;
    }

    public Map<String, Object> login(LoginBody loginBody) {
        User user = null;
        LoginType loginType = loginBody.getLoginType();
        String param = loginBody.getParam();
        String account = loginBody.getAccount();
        if (LoginType.PASSWORD == loginType && !StringUtils.isEmpty(param)) {
            if (StringUtils.isEmpty(account)) {
                throw BusinessException.instance(ErrorCode.E_PARAM);
            }
            user = loginByPassword(loginBody);
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

    public User loginByPassword(LoginBody loginBody) {
        List<User> userList = new ArrayList<>();
        User user = null;
        String password = loginBody.getParam();
        String account = loginBody.getAccount();
        if (SpringUtil.isEmail(account)) {
            user = userRepository.findFirstByEmailAndStatus(account, EnableStatus.ENABLE);
        } else if (SpringUtil.isPhone(account)) {
            user = userRepository.findFirstByMobileAndStatus(account, EnableStatus.ENABLE);
        } else {
            user = userRepository.findFirstByNameAndStatus(account, EnableStatus.ENABLE);
        }
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
        loginUser.setLoginCount(loginUser.getLoginCount() + 1);
        loginUser.setLoginTime(new Date());
        userRepository.save(loginUser);
        return loginUser;
    }

    public User registerByEmail(String email, String password) {
        String lockKey = "registerByEmail-" + email;
        String lock = MemoryCache.getLock(lockKey);
        synchronized (lock) {
            return registerByEmail_(email, password);
        }
    }

    // 邮箱注册
    public synchronized User registerByEmail_(String email, String password) {
        //参数验证
        if (StringUtils.isEmpty(email)) {
            throw new BusinessException(ErrorCode.E_PARAM);
        }
        //是否已经存在
        User user = userRepository.findFirstByEmailAndStatus(email, EnableStatus.ENABLE);
        if (user == null) {
//            throw new BusinessException(ErrorCode.USER_HAD_EXISTS);
            user = createAndSaveUser(email);
        }
        userSecurityService.setUserPassword(user.getId(), password);
        return user;
    }

    private /*synchronized*/ User createAndSaveUser(String email) {
        User user = new User();
        if (!StringUtils.isEmpty(email)) {
            user.setEmail(email);
        }
        user = userRepository.save(user);
        int userId = user.getId();
        return userRepository.save(user);
    }
}