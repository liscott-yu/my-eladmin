package com.scott.service.mapstruct;

import com.scott.base.BaseMapper;
import com.scott.domain.User;
import com.scott.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * project name  my-eladmin-backend1
 * filename  UserMapper
 * @author liscott
 * @date 2022/12/29 10:34
 * description  TODO
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
}
