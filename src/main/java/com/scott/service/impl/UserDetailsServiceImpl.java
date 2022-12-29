package com.scott.service.impl;

import com.scott.service.DataService;
import com.scott.service.RoleService;
import com.scott.service.UserService;
import com.scott.service.dto.JwtUserDto;
import com.scott.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  UserDetailsServiceImpl
 * @author liscott
 * @date 2022/12/28 18:25
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    DataService dataService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.findByName(username);
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("Admin");
        JwtUserDto jwtUserDto = new JwtUserDto(
                userDto,
                // 获取当前用户的数据权限，并进行授权
                dataService.getDeptIds(userDto),
                // 获取当前用户的权限，并进行授权
                roleService.mapToGrantedAuthorities(userDto)
        );
        return jwtUserDto;
    }
}
