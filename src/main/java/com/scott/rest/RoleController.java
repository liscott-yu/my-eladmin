package com.scott.rest;

import com.scott.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project name  my-eladmin-backend1
 * filename  RoleService
 * @author liscott
 * @date 2022/12/30 15:59
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：角色管理")
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyAuthority('role:list', 'user:add', 'user:edit', 'admin')")
    public ResponseEntity<Object> queryAllRole() {
        return new ResponseEntity<>(roleService.queryAll(), HttpStatus.OK);
    }

}
