package com.scott.service;

import com.scott.domain.User;
import com.scott.service.dto.UserDto;
import com.scott.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * project name  my-eladmin-backend1
 * filename  UserService
 * @author liscott
 * @date 2022/12/29 10:36
 */
@Service
public interface UserService {
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
}
