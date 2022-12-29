package com.scott.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * project name  my-eladmin-backend1
 * filename  JwtUserDto
 *
 * @author liscott
 * @date 2022/12/28 18:05
 * description  认证的主体
 * 身份证认证系统 -> 主体 -> 身份证  （身份证号码，姓名，户籍所在地，头像）
 */
@Getter
@AllArgsConstructor
public class JwtUserDto implements UserDetails {

    private final UserDto user;
    private final List<Long> dataScope;
    @JsonIgnore
    private final List<GrantedAuthority> authorities;

   public Set<String> getRoles() {
       return authorities.stream()
               .map(GrantedAuthority::getAuthority)
               .collect(Collectors.toSet());
   }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
