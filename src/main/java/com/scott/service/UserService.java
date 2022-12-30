package com.scott.service;

import com.scott.service.dto.UserDto;
import com.scott.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * project name  my-eladmin-backend1
 * filename  UserService
 * @author liscott
 * @date 2022/12/29 10:36
 */
public interface UserService {
    /**
     * 根据用户名查询 UserDto
     * @param username  username
     * @return UserDto
     */
    UserDto findByName(String username);

    /**
     * queryAll
     * @param UserQueryCriteria, Pageable /
     * @return /
     */
    Object queryAll(UserQueryCriteria userQueryCriteria, Pageable pageable);
}
