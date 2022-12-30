package com.scott.rest;

import com.scott.service.MenuService;
import com.scott.service.dto.MenuDto;
import com.scott.service.mapstruct.MenuMapper;
import com.scott.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  MenuController
 * @author liscott
 * @date 2022/12/29 13:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;
    private final MenuMapper menuMapper;

    @GetMapping(value = "/build")
    @ApiOperation("获取前端所需菜单")
    public ResponseEntity<Object> buildMenus() {
        // 获取当前用户 所有的 菜单列表，非树形结构
        List<MenuDto> menuDtoList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        // 把获取回来的 菜单列表 转化成 树形菜单列表
        List<MenuDto> menuDtos = menuService.buildTree(menuDtoList);
        // 把DTO 转化为 VO
        return new ResponseEntity<>(menuService.buildMenus(menuDtos), HttpStatus.OK);

    }
}
