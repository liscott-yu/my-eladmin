package com.scott.service.dto;

import com.scott.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * project name  my-eladmin-backend1
 * filename  JobDto
 *
 * @author liscott
 * @date 2022/12/30 13:59
 * description  TODO
 */
@Setter
@Getter
@NoArgsConstructor
public class JobDto extends BaseDTO implements Serializable {
    private Long id;
    private Integer jobSort;
    private String name;
    private Boolean enabled;

    public JobDto(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
