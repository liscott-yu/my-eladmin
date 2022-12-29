package com.scott.repository;

import com.scott.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  RoleRepository
 * @author liscott
 * @date 2022/12/29 11:33
 * description  TODO
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    /**
     * 根据 用户ID 查询 角色集合
     * @param id 用户ID
     * @return 角色集合
     */
    @Query(value = "SELECT r.* FROM sys_role r, sys_users_roles ur WHERE " +
        "r.role_id = ur.role_id AND ur.user_id = ?1", nativeQuery = true)
    Set<Role> findByUserId(Long id);
}
