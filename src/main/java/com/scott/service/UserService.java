package com.scott.service;

import com.scott.domain.User;
import com.scott.service.dto.UserDto;
import com.scott.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  UserService
 * @author liscott
 * @date 2022/12/29 10:36
 */
@Service
public interface UserService {

    /**
     * 根据ID 查询 UserDto
     * @param id String
     * @return UserDto
     */
    UserDto findById(long id);

    /**
     * 根据用户名查询 UserDto
     * @param username  username
     * @return UserDto
     */
    UserDto findByName(String username);

    /**
     * queryAll
     * @param userQueryCriteria, pageable  /
     * @return /
     */
    Object queryAll(UserQueryCriteria userQueryCriteria, Pageable pageable);
    /**
     * 新增用户
     * @param resources /
     */
    void create(User resources);

    /**
     * 修改 用户
     * @param resources User
     * @throws Exception /
     */
    void update(User resources) throws Exception;

    /**
     * 删除用户
     * @param ids Set<Long>
     */
    void delete(Set<Long> ids);
}
