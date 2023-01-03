package com.scott.service.impl;

import com.scott.domain.User;
import com.scott.exception.EntityExistException;
import com.scott.repository.UserRepository;
import com.scott.service.UserService;
import com.scott.service.dto.UserDto;
import com.scott.service.dto.UserQueryCriteria;
import com.scott.service.mapstruct.UserMapper;
import com.scott.utils.PageUtil;
import com.scott.utils.QueryHelp;
import com.scott.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

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
    @Transactional(rollbackFor = Exception.class)
    public UserDto findById(long id) {
        User user = userRepository.findById(id).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto findByName(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toDto(user);
    }

    @Override
    public Object queryAll(UserQueryCriteria userQueryCriteria, Pageable pageable) {
        Page<User> pages = userRepository.findAll(
                (root, query, cb) -> QueryHelp.getPredicate(root, userQueryCriteria, cb), pageable );
        // 以上代码等价于以下代码：
//        Page<User> page = userRepository.findAll(new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return QueryHelp.getPredicate(root, query, criteriaBuilder);
//            }
//        }, pageable);
        return PageUtil.toPage(pages.map(userMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User resources) {
        if(userRepository.findByUsername(resources.getUsername()) != null){
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if(userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        if(userRepository.findByPhone(resources.getPhone()) != null) {
            throw new EntityExistException(User.class, "phone", resources.getPhone());
        }
        userRepository.save(resources);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) throws Exception {
        // 查询用户是否已存在， 若不存在，无法更新
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "Id", resources.getId());
        // 用户 的 Username， Phone， Email 不可重复
        User byUsername = userRepository.findByUsername(resources.getUsername());
        User byPhone = userRepository.findByPhone(resources.getPhone());
        User byEmail = userRepository.findByEmail(resources.getEmail());
        if(byUsername != null && !user.getId().equals(byUsername.getId())){
            throw new EntityExistException(user.getClass(), "username", resources.getUsername());
        }
        if(byPhone != null && !user.getId().equals(byPhone.getId())) {
            throw new EntityExistException(user.getClass(), "phone", resources.getPhone());
        }
        if(byEmail != null && !user.getId().equals(byEmail.getId())) {
            throw new EntityExistException(user.getClass(), "email", resources.getEmail());
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());
        user.setDept(resources.getDept());
        user.setJobs(resources.getJobs());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setGender(resources.getGender());
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        userRepository.deleteAllByIdIn(ids);
    }
}
