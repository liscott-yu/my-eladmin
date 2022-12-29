package com.scott.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.scott.domain.Dept;
import com.scott.service.DataService;
import com.scott.service.DeptService;
import com.scott.service.RoleService;
import com.scott.service.dto.UserDto;
import com.scott.service.dto.small.RoleSmallDto;
import com.scott.utils.enums.DataScopeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * project name  my-eladmin-backend1
 * filename  DataServiceImpl
 * @author liscott
 * @date 2022/12/29 17:05
 * description  数据权限 服务 实现
 */
@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final RoleService roleService;
    private final DeptService deptService;

    @Override
    public List<Long> getDeptIds(UserDto user) {
        // 用于 存储 部门 id
        Set<Long> deptIds = new HashSet<>();
        // 查询用户角色
        List<RoleSmallDto> roleSet = roleService.findByUsersId(user.getId());
        // 获取对应部门 ID
        for (RoleSmallDto role : roleSet) {
            DataScopeEnum dataScopeEnum = DataScopeEnum.find(role.getDataScope());
            switch (Objects.requireNonNull(dataScopeEnum)) {
                case THIS_LEVEL:
                    deptIds.add(user.getDept().getId());
                    break;
                case CUSTOMIZE:
                    deptIds.addAll(getCustomize(deptIds, role));
                    break;
                default:
                    return new ArrayList<>(deptIds);
            }
        }
        return new ArrayList<>(deptIds);
    }

    /**
     * 获取自定义的数据权限，也就是获取sys_roles_depts表里面配置的记录
     * @param deptIds 部门ID
     * @param role 角色
     * @return 数据权限ID
     */
    public Set<Long> getCustomize(Set<Long> deptIds, RoleSmallDto role) {
        //根据sys_roles_depts表获取当前用户角色对应的部门id
        Set<Dept> depts = deptService.findByRoleId(role.getId());
        for (Dept dept : depts) {
            deptIds.add(dept.getId());
            //若这个部门有下级部门，则获取它的下级部门
            //这样数据库表sys_roles_depts只需配最高级部门的id即可看到它下面的所有部门
            List<Dept> deptChildren = deptService.findByPid(dept.getId());
            if (deptChildren != null && deptChildren.size() != 0){
                deptIds.addAll(deptService.getDeptChildren(deptChildren));
            }
        }
        return deptIds;
    }
}
