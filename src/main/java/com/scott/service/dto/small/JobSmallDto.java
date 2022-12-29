package com.scott.service.dto.small;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * project name  my-eladmin-backend1
 * filename  JobSmallDto
 *
 * @author liscott
 * @date 2022/12/28 17:50
 * description  TODO
 */
@Data
@NoArgsConstructor
public class JobSmallDto implements Serializable {
    private Long id;

    private String name;
}
