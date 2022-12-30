package com.scott.service.mapstruct;

import com.scott.base.BaseMapper;
import com.scott.domain.Role;
import com.scott.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * project name  my-eladmin-backend1
 * filename  RoleMapper
 * @author liscott
 * @date 2022/12/30 13:49
 * description  TODO
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {
}
