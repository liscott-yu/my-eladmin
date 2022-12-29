package com.scott.repository;

import com.scott.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * project name  my-eladmin-backend1
 * filename  UserRepository
 *
 * @author liscott
 * @date 2022/12/29 10:30
 * description  <User, Long>前者代表要查哪个表，后者代表id的类型
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查询用户
     * @param username String 用户名
     * @return User 用户
     */
    User findByUsername(String username);
}
