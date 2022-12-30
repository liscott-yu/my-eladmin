package com.scott.rest;

import com.scott.domain.User;
import com.scott.exception.BadRequestException;
import com.scott.service.RoleService;
import com.scott.service.UserService;
import com.scott.service.dto.UserQueryCriteria;
import com.scott.service.dto.small.RoleSmallDto;
import com.scott.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.stream.Collectors;
/**
 * project name  my-eladmin-backend1
 * filename  UserController
 * @author liscott
 * @date 2022/12/29 15:40
 * description  TODO
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation("查询用户")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:list','admin')")
    public ResponseEntity<Object> queryUser(UserQueryCriteria userQueryCriteria, Pageable pageable) {
        return new ResponseEntity<>(userService.queryAll(userQueryCriteria, pageable), HttpStatus.OK);
    }

    @ApiOperation("新增用户")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add','admin')")
    public ResponseEntity<Object> createUser(@Validated @RequestBody User resources) {
        checkLevel(resources);
        // 默认密码 123456
        resources.setPassword(passwordEncoder.encode("123456"));
        userService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 如果当前用户的角色级别低于创建用户的角色级别，则抛出权限不足的错误
     * @param resources /
     */
    private void checkLevel(User resources) {
        //当前登陆用户的角色级别
        Integer currentLevel =  Collections.min(roleService.findByUsersId( SecurityUtils.getCurrentUserId() )
                .stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
        //新用户的角色级别
        Integer optLevel = roleService.findByRoles(resources.getRoles());
        //level 1是最高级别，数字越大级别越低
        if (currentLevel > optLevel) {
            throw new BadRequestException("角色权限不足");
        }
    }

}
