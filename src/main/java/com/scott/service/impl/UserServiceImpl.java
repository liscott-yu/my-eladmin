package com.scott.service.impl;

import com.scott.domain.User;
import com.scott.repository.UserRepository;
import com.scott.service.UserService;
import com.scott.service.dto.UserDto;
import com.scott.service.mapstruct.UserMapper;
import com.scott.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Object queryAll(Pageable pageable) {
        Page<User> pages = userRepository.findAll(pageable);
        return PageUtil.toPage(pages.map(userMapper::toDto));
    }
}
