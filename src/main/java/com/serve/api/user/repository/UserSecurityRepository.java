package com.serve.api.user.repository;

import com.serve.api.user.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Integer> {
    UserSecurity findByUserId(int userId);
    UserSecurity findByWeChatOpenId(String  wechatOpenId);
    UserSecurity findFirstByWeChatOpenId(String  wechatOpenId);
    UserSecurity findByAppleOpenId(String  appleOpenId);
    UserSecurity findFirstByWechatGzhopenid(String  wechatGzhOpenid);
    List<UserSecurity> findByUserIdIn(List<Integer> uids);

}
