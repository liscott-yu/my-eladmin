package com.scott.rest;

import com.scott.service.DeptService;
import com.scott.service.dto.DeptDto;
import com.scott.service.dto.DeptQueryCriteria;
import com.scott.utils.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  DeptController
 *
 * @author liscott
 * @date 2022/12/30 15:45
 * description  TODO
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：部门管理")
@RequestMapping("/api/dept")
public class DeptController {

    private final DeptService deptService;

    @ApiOperation("查询部门")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:list', 'dept:list', 'admin')")
    public ResponseEntity<Object> queryDept(DeptQueryCriteria criteria) throws Exception {
        List<DeptDto> deptDtos = deptService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(deptDtos, deptDtos.size()), HttpStatus.OK);
    }

}
