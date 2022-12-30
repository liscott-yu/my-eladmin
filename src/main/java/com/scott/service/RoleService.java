package com.scott.service;

import com.scott.domain.Role;
import com.scott.service.dto.RoleDto;
import com.scott.service.dto.UserDto;
import com.scott.service.dto.small.RoleSmallDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  RoleService
 * @author liscott
 * @date 2022/12/29 13:21
 */
public interface RoleService {
    /**
     * 根据 用户ID 查询 小角色列表
     * @param id 用户ID
     * @return /
     */
    List<RoleSmallDto> findByUsersId(Long id);

    /**
     * 获取 用户权限信息列表
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto user);

    /**
     * 根据角色查询角色级别
     * @param roles /
     * @return /
     */
    Integer findByRoles(Set<Role> roles);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    RoleDto findById(long id);

    /**
     * 查询全部数据
     * @return /
     */
    List<RoleDto> queryAll();
}
