package com.scott.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.scott.domain.Menu;
import com.scott.domain.vo.MenuMetaVo;
import com.scott.domain.vo.MenuVo;
import com.scott.repository.MenuRepository;
import com.scott.service.MenuService;
import com.scott.service.RoleService;
import com.scott.service.dto.MenuDto;
import com.scott.service.dto.small.RoleSmallDto;
import com.scott.service.mapstruct.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * project name  my-eladmin-backend1
 * filename  MenuServiceImpl
 * @author liscott
 * @date 2022/12/29 13:22
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final RoleService roleService;

    /**
     * 获取 当前用户 能看到的 菜单
     * 用户角色改变时需清理缓存
     * @param currentUserId /
     * @return /
     */
    @Override
    @Cacheable(key = "'user:' + #p0")
    public List<MenuDto> findByUser(Long currentUserId) {
        // 获取 当前登录用户 的 所有角色
        List<RoleSmallDto> roles = roleService.findByUsersId(currentUserId);
        // 把 角色集合 转化为 角色Id集合
        Set<Long> roleIds = roles.stream()
                .map(RoleSmallDto::getId)
                .collect(Collectors.toSet());
        // 根据角色查询能看到的菜单，且菜单类型不能为2，类型为2的菜单是具体权限（或按钮）如：用户新增
        LinkedHashSet<Menu> menus = menuRepository.findByRoleIdsAndTypeNot(roleIds, 2);
        return menus.stream().map(menuMapper::toDto).collect(Collectors.toList());
    }

    /**
     * 构建 菜单树
     * @param menuDtos 用户能看到的菜单集合
     * @return /
     */
    @Override
    public List<MenuDto> buildTree(List<MenuDto> menuDtos) {
        // 顶层菜单的集合
        List<MenuDto> trees = new ArrayList<>();
        //子菜单id的集合，避免重复所以使用Set而不是List
        Set<Long> ids = new HashSet<>();
        // 遍历 用户 能看到的 每个菜单
        for (MenuDto menuDTO : menuDtos) {
            // 若没有上级菜单，代表一级菜单，添加进 trees 里面
            if( menuDTO.getPid() == null ){
                trees.add(menuDTO);
            }
            // 再次遍历用户能看到的每个菜单
            for ( MenuDto it : menuDtos ) {
                // 找出 menuDTO 的 下级菜单
                if(menuDTO.getId().equals(it.getPid())) {
                    // 添加 子菜单
                    if(menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        // 若 该用户 无 顶级菜单 权限
        if(trees.size() == 0){
            //在用户能看到的菜单集合里面筛选出所有非子菜单，意思就是一级菜单没有，就显示二级菜单，以此类推
            trees = menuDtos.stream().filter( s -> !ids.contains(s.getId()))
                    .collect(Collectors.toList());
        }
        return trees;
    }

    /**
     * 完成从menuDTO到menuVO的转化
     * @param menuDtos 已是 菜单树
     * @return
     */
    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menuDtos) {
        List<MenuVo> trees = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getTitle());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == null ? "/" + menuDTO.getPath() :menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getTitle(),menuDTO.getIcon(),!menuDTO.getCache()));
                        //处理子菜单
                        List<MenuDto> menuDtoList = menuDTO.getChildren();
                        if(CollectionUtil.isNotEmpty(menuDtoList)){
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                        }
                        trees.add(menuVo);
                    }
                }
        );
        return trees;
    }

}
