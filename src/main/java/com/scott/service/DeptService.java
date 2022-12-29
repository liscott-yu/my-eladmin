package com.scott.service;

import com.scott.domain.Dept;

import java.util.List;
import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  DeptService
 * @author liscott
 * @date 2022/12/29 17:00
 * description  TODO
 */
public interface DeptService {
    /**
     * 根据PID查询 List<Dept>
     * @param pid /
     * @return /
     */
    List<Dept> findByPid(long pid);

    /**
     * 根据角色ID查询 Set<Dept>
     * @param id /
     * @return /
     */
    Set<Dept> findByRoleId(Long id);

    /**
     * 获取下级部门
     * @param deptList /
     * @return /
     */
    List<Long> getDeptChildren(List<Dept> deptList);
}
