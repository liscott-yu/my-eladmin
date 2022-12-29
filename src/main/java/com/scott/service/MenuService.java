package com.scott.service;

import com.scott.service.dto.MenuDto;

import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  MenuService
 * @author liscott
 * @date 2022/12/29 13:18
 */

public interface MenuService {
    /**
     * 构建菜单树
     * @param menuDtos /
     * @return /
     */
    Object buildMenus(List<MenuDto> menuDtos);

    /**
     * 构建菜单树
     * @param menuDtos 原始数据
     * @return /
     */
    List<MenuDto> buildTree(List<MenuDto> menuDtos);

    /**
     * 根据当前用户获取 菜单列表
     * @param currentUserId /
     * @return /
     */
    List<MenuDto> findByUser(Long currentUserId);
}
