package com.scott.service.mapstruct;

import com.scott.base.BaseMapper;
import com.scott.domain.Dept;
import com.scott.service.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * project name  my-eladmin-backend1
 * filename  DeptMapper
 *
 * @author liscott
 * @date 2022/12/30 14:04
 * description  TODO
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {
}
