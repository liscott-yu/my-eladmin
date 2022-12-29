package com.scott.repository;

import com.scott.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  MenuRepository
 * @author liscott
 * @date 2022/12/29 11:43
 * description  TODO
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {
    /**
     * 根据 角色ID集合 与 菜单类型 查询 菜单集合
     * @param roleIds 角色ID集合
     * @param type 菜单类型
     * @return 菜单集合
     */
    @Query(value = "SELECT m.* FROM sys_menu m, sys_roles_menus rm WHERE " +
            "m.menu_id = rm.menu_id AND rm.role_id IN ?1 AND type != ?2 " +
            "order by m.menu_sort asc ",nativeQuery = true)
    LinkedHashSet<Menu> findByRoleIdsAndTypeNot(Set<Long> roleIds, int type);
}
