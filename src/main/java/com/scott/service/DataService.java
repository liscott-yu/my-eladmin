package com.scott.service;

import com.scott.service.dto.UserDto;

import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  DataService
 * @author liscott
 * @date 2022/12/29 17:02
 * description  数据权限 服务类
 */
public interface DataService {
    /**
     * 获取数据权限
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);
}
