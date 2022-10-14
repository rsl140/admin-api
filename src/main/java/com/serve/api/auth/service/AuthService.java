package com.serve.api.auth.service;


import com.serve.api.auth.entity.Menu;
import com.serve.api.auth.entity.Role;
import com.serve.api.auth.repository.MenuRepository;
import com.serve.api.auth.repository.RoleRepository;
import com.serve.api.comm.utils.CommUtils;
import com.serve.api.user.entity.User;
import com.serve.api.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Lazy
public class AuthService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Menu> getMenuList() {
        return menuRepository.findAll();
    }

    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    public Page<User> findUserBy(String name, String mobile, String email, Integer roleId, Integer pageNo, Integer pageSize) {
        //动态查询
        Specification<User> sf = (Specification<User>) (root, query, cb) -> {
            //用于添加所有查询条件
            List<Predicate> p = new ArrayList<>();
            if (!StringUtils.isEmpty(name)) {
                Predicate p1 = cb.like(root.get("name").as(String.class), "%" + name + "%");
                p.add(p1);
            }
            if (!StringUtils.isEmpty(mobile)) {
                Predicate p2 = cb.like(root.get("mobile").as(String.class), "%" + mobile + "%");
                p.add(p2);
            }
            if (!StringUtils.isEmpty(email)) {
                Predicate p3 = cb.like(root.get("email").as(String.class), "%" + email + "%");
                p.add(p3);
            }
            Predicate[] pre = new Predicate[p.size()];
            Predicate and = cb.and(p.toArray(pre));     //查询条件 and
            //Predicate or = cb.or(p.toArray(pre));       //查询条件 or
            query.where(and);       //添加查询条件

            //设置排序
            List<Order> orders = new ArrayList<>();
            orders.add(cb.desc(root.get("id")));       //倒序
//            orders.add(cb.asc(root.get("username")));   //正序
            return query.orderBy(orders).getRestriction();
        };
        Pageable pageable = CommUtils.getPageable(pageNo, pageSize);
        return userRepository.findAll(sf, pageable);
    }
}