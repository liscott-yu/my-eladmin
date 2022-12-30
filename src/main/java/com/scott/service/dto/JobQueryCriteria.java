package com.scott.service.dto;

import com.scott.annotation.Query;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * project name  my-eladmin-backend1
 * filename  JobQueryCriteria
 * @author liscott
 * @date 2022/12/30 14:01
 * description  TODO
 */
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query
    private Boolean enabled;
}
