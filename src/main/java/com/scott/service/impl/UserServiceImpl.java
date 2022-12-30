package com.scott.service.impl;

import com.scott.domain.User;
import com.scott.repository.UserRepository;
import com.scott.service.UserService;
import com.scott.service.dto.UserDto;
import com.scott.service.dto.UserQueryCriteria;
import com.scott.service.mapstruct.UserMapper;
import com.scott.utils.PageUtil;
import com.scott.utils.QueryHelp;
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
    public Object queryAll(UserQueryCriteria userQueryCriteria, Pageable pageable) {
        Page<User> pages = userRepository.findAll(
                (root, query, cb) -> QueryHelp.getPredicate(root, userQueryCriteria, cb), pageable
                );
        // 以上代码等价于以下代码：
//        Page<User> page = userRepository.findAll(new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return QueryHelp.getPredicate(root, query, criteriaBuilder);
//            }
//        }, pageable);
        return PageUtil.toPage(pages.map(userMapper::toDto));
    }
}
