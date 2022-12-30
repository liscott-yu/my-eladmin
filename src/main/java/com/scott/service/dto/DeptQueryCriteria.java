package com.scott.service.dto;

import com.scott.annotation.DataPermission;
import com.scott.annotation.Query;
import lombok.Data;

/**
 * project name  my-eladmin-backend1
 * filename  DeptQueryCriteria
 * @author liscott
 * @date 2022/12/30 13:55
 * description  TODO
 */
@Data
@DataPermission(fieldName = "id")
public class DeptQueryCriteria {

    @Query
    private Boolean enabled;

    @Query
    private Long pid;

    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;
}
