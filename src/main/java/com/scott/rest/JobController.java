package com.scott.rest;

import com.scott.service.JobService;
import com.scott.service.dto.JobQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * project name  my-eladmin-backend1
 * filename  JobController
 *
 * @author liscott
 * @date 2022/12/30 15:52
 * description  TODO
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：岗位管理")
@RequestMapping("/api/job")
public class JobController {
    private final JobService jobService;

    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('job:list', 'user:list', 'admin')")
    public ResponseEntity<Object> queryJob(JobQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(jobService.queryAll(criteria, pageable), HttpStatus.OK);
    }
}
