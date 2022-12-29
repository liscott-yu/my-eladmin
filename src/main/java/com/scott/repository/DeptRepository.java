package com.scott.repository;

import com.scott.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  DeptRepository
 * @author liscott
 * @date 2022/12/29 16:52
 * description  TODO
 */
public interface DeptRepository extends JpaRepository<Dept, Long>, JpaSpecificationExecutor<Dept> {
    /**
     * 根据 PID 查询 List<Dept>
     * @param id pid
     * @return /
     */
    List<Dept> findByPid(Long id);

    /**
     * 根据角色ID 查询 Set<Dept>
     * @param roleId 角色ID
     * @return /
     */
    //nativeQuery=true表示使用原生SQL，from后面接数据库表名
    //nativeQuery=false表示使用HQL，from后面接实体类名
    @Query(value = "SELECT d.* FROM sys_dept d, sys_roles_depts rd where " +
            "d.dept_id = rd.dept_id AND rd.role_id = ?1", nativeQuery = true)
    Set<Dept> findByRoleId(Long roleId);
}
