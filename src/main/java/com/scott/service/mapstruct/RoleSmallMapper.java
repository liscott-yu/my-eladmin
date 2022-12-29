package com.scott.service.mapstruct;

import com.scott.base.BaseMapper;
import com.scott.domain.Role;
import com.scott.service.dto.small.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * project name  my-eladmin-backend1
 * filename  RoleSmallMapper
 * @author liscott
 * @date 2022/12/29 14:19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {

}
