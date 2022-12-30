package com.scott.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.scott.domain.Dept;
import com.scott.repository.DeptRepository;
import com.scott.service.DeptService;
import com.scott.service.dto.DeptDto;
import com.scott.service.dto.DeptQueryCriteria;
import com.scott.service.mapstruct.DeptMapper;
import com.scott.utils.QueryHelp;
import com.scott.utils.SecurityUtils;
import com.scott.utils.enums.DataScopeEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  DeptServiceImpl
 * @author liscott
 * @date 2022/12/29 17:27
 * description  TODO
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {
    private final DeptRepository deptRepository;
    private final DeptMapper deptMapper;

    @Override
    public List<DeptDto> queryAll(DeptQueryCriteria criteria, Boolean isQuery) throws Exception {
        Sort sort = Sort.by(Sort.Direction.ASC, "deptSort");
        String dataScopeType = SecurityUtils.getDataScopeType();
        if (isQuery) {
            // ALL
            if(dataScopeType.equals(DataScopeEnum.ALL.getValue())){
                criteria.setPidIsNull(true);
            }
            List<Field> fields = Arrays.asList(criteria.getClass().getDeclaredFields());
            List<String> fieldNames = new ArrayList<String>() {{
                add("pidIsNull");
                add("enabled");
            }};
            for (Field field : fields) {
                //设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                Object val = field.get(criteria);
                if(fieldNames.contains(field.getName())) {
                    continue;
                }
                if (ObjectUtil.isNotNull(val)) {
                    criteria.setPidIsNull(null);
                    break;
                }
            }
        }
        List<DeptDto> list = deptMapper.toDto(deptRepository.findAll((root, query, criteriaBuilder)
                -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), sort));
        // 如果为空，就代表为 自定义权限或者本级权限，就需要去重，不理解可以注释掉，看查询结果
        if(StringUtils.isBlank(dataScopeType)){
            return deduplication(list);
        }
        return list;
    }

    /**
     * 去重，去掉所有子部门，留下最顶级的部门
     */
    private List<DeptDto> deduplication(List<DeptDto> list) {
        // 存 顶级部门
        List<DeptDto> deptDtos = new ArrayList<>();
        // 遍历
        for (DeptDto deptDto : list) {
            boolean flag = true;
            // 遍历
            for (DeptDto dto : list) {
                // deptDto 有 pid , 标记 false， 跳出
                if (dto.getId().equals(deptDto.getPid())){
                    flag = false;
                    break;
                }
            }
            if(flag) {
                deptDtos.add(deptDto);
            }
        }
        return deptDtos;
    }

    @Override
    public List<Dept> findByPid(long pid) {
        return deptRepository.findByPid(pid);
    }

    @Override
    public Set<Dept> findByRoleId(Long id) {
        return deptRepository.findByRoleId(id);
    }

    @Override
    public List<Long> getDeptChildren(List<Dept> deptList) {
        List<Long> list = new ArrayList<>();
        deptList.forEach( dept -> {
            if(dept != null && dept.getEnabled()) {
                List<Dept> depts = deptRepository.findByPid(dept.getId());
                if(depts.size() != 0) {
                    list.addAll(getDeptChildren(depts));
                }
                list.add(dept.getId());
            }
        });
        return list;
    }
}
