package com.scott.repository;

import com.scott.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  UserRepository
 * @author liscott
 * @date 2022/12/29 10:30
 * description  <User, Long>前者代表要查哪个表，后者代表id的类型
 *继承JpaRepository之后，会默认使用SimpleJpaRepository这个实现类作为UserRepository接口的实体bean
 * 继承JpaSpecificationExecutor是为了实现动态查询
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查询用户
     * @param username String 用户名
     * @return User 用户
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查询
     * @param email 邮箱
     * @return User 用户
     */
    User findByEmail(String email);

    /**
     * 根据手机号查询
     * @param phone 手机号
     * @return User 用户
     */
    User findByPhone(String phone);

    /**
     * 根据Id删除
     * @param ids Set<Long>
     */
    void deleteAllByIdIn(Set<Long> ids);
}
