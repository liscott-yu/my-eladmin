package com.scott.service.mapstruct;

import com.scott.base.BaseMapper;
import com.scott.domain.Job;
import com.scott.service.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * project name  my-eladmin-backend1
 * filename  JobMapper
 * @author liscott
 * @date 2022/12/30 14:06
 * description  TODO
 */
@Mapper(componentModel = "spring", uses = {DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapper<JobDto, Job> {
}
