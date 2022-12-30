package com.scott.service.impl;

import com.scott.domain.Menu;
import com.scott.domain.Role;
import com.scott.repository.RoleRepository;
import com.scott.service.RoleService;
import com.scott.service.dto.RoleDto;
import com.scott.service.dto.UserDto;
import com.scott.service.dto.small.RoleSmallDto;
import com.scott.service.mapstruct.RoleMapper;
import com.scott.service.mapstruct.RoleSmallMapper;
import com.scott.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * project name  my-eladmin-backend1
 * filename  RoleServiceImpl
 * @author liscott
 * @date 2022/12/29 14:13
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleSmallMapper roleSmallMapper;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
        return roleSmallMapper.toDto( new ArrayList<>(roleRepository.findByUserId(id)));
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<String> permissions = new HashSet<>();
        // 若是 管理员 直接返回
        if(user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        //roles.stream(): 角色的流 stream(role1, role2, role3 ...)
        //roles.stream().flatMap:  菜单的流的流     stream(steam(menus) steam(menus) steam(menus)...)
        //roles.stream().flatMap: 菜单的流    stream(menu menu menu ...)
        //map跟前端原理一样，把一个流变成另外一个流。
        Set<Role> roles = roleRepository.findByUserId(user.getId());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        if( roles.size() == 0){
            return Integer.MAX_VALUE;
        }
        Set<RoleDto> roleDtos = new HashSet<>();
        // Role -> RoleDto
        for(Role role : roles) {
            roleDtos.add(findById(role.getId()));
        }
        // min, RoleDto::getLevel
        return Collections.min( roleDtos.stream().map(RoleDto::getLevel).collect(Collectors.toList()) );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleDto findById(long id) {
        Role role = roleRepository.findById(id).orElseGet(Role::new);
        ValidationUtil.idNull(role.getId(), "Role", "id", id);
        return roleMapper.toDto(role);
    }

    @Override
    public List<RoleDto> queryAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "level");
        return roleMapper.toDto(roleRepository.findAll(sort));
    }
}
