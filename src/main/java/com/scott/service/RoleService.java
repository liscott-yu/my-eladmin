package com.scott.service;

import com.scott.service.dto.small.RoleSmallDto;
import java.util.List;

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
}
