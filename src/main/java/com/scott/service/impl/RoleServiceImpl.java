package com.scott.service.impl;

import com.scott.repository.RoleRepository;
import com.scott.service.RoleService;
import com.scott.service.dto.small.RoleSmallDto;
import com.scott.service.mapstruct.RoleSmallMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  RoleServiceImpl
 * @author liscott
 * @date 2022/12/29 14:13
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleSmallMapper roleSmallMapper;

    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
        return roleSmallMapper.toDto( new ArrayList<>(roleRepository.findByUserId(id)));
    }
}
