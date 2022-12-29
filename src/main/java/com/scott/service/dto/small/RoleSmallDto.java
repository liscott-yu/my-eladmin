package com.scott.service.dto.small;

import lombok.Data;

import java.io.Serializable;

/**
 * project name  my-eladmin-backend1
 * filename  RoleSmallDto
 *
 * @author liscott
 * @date 2022/12/28 17:49
 * description  TODO
 */
@Data
public class RoleSmallDto implements Serializable {
    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
