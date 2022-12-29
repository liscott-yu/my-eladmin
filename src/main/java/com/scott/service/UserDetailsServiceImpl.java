package com.scott.service;

import com.scott.service.dto.JwtUserDto;
import com.scott.service.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  UserDetailsServiceImpl
 *
 * @author liscott
 * @date 2022/12/28 18:25
 * description  TODO
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!"admin".equals(username)) {
            throw new UsernameNotFoundException("");
        }
        UserDto userDto = new UserDto();
        userDto.setEnabled(true);
        userDto.setNickName("scott");
        userDto.setPassword("$2a$10$.a.DezMXYcnDOKxlL7JKDuRxt9VrZLyJHdjGHklNY6mo3FcTRY/GW");
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("Admin");
        JwtUserDto jwtUserDto = new JwtUserDto(
                userDto,
                null,
                authorities
        );
        return jwtUserDto;
    }
}
