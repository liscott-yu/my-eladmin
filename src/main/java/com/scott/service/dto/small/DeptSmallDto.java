package com.scott.service.dto.small;

import lombok.Data;

import java.io.Serializable;

/**
 * project name  my-eladmin-backend1
 * filename  DeptSmallDto
 *
 * @author liscott
 * @date 2022/12/28 17:51
 * description  TODO
 */
@Data
public class DeptSmallDto implements Serializable {
    private Long id;

    private String name;
}
