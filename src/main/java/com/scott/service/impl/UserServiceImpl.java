package com.scott.service.impl;

import com.scott.domain.User;
import com.scott.repository.UserRepository;
import com.scott.service.UserService;
import com.scott.service.dto.UserDto;
import com.scott.service.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * project name  my-eladmin-backend1
 * filename  UserServiceImpl
 * @author liscott
 * @date 2022/12/29 10:43
 * description  TODO
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto findByName(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toDto(user);
    }
}
