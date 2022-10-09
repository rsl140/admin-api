package com.serve.api.user.repository;

import com.serve.api.comm.enums.EnableStatus;
import com.serve.api.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByMobileAndStatus(String mobile, EnableStatus status);

    User findFirstByEmailAndStatus(String email, EnableStatus status);

    User findFirstByName(String name);

    List<User> findByMobileIn(List<String> mobileList);

    List<User> findByMobileIn(String[] mobileList);

    List<User> findByIdIn(Set<Integer> userIdList);

    List<User> findByIdIn(List<Integer> userIdList);

    Page<User> findByStatusAndIdNotOrderByIdDesc(EnableStatus status, int userId, Pageable pageable);

    Page<User> findByStatusAndHeadUrlIsNotNullAndIdNotOrderByIdDesc(EnableStatus status, int userId, Pageable pageable);

//    @Query(nativeQuery = true, value = "sql ?1 ?2")
//    List<User> findByxxx(List<Integer> userIdList, Integer id);
//    'select * from xx and'

}
