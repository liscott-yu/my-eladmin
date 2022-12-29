package com.scott.service.mapstruct;

import com.scott.base.BaseMapper;
import com.scott.domain.Menu;
import com.scott.service.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * project name  my-eladmin-backend1
 * filename  MenuMapper
 * @author liscott
 * @date 2022/12/29 13:26
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, Menu> {
}
