package com.scott.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.scott.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * project name  my-eladmin-backend1
 * filename  DeptDto
 * @author liscott
 * @date 2022/12/30 13:53
 * description  TODO
 */
@Getter
@Setter
public class DeptDto extends BaseDTO implements Serializable {
    private Long id;
    private String name;
    private Boolean enabled;
    private Integer deptSort;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDto> children;
    private Long pid;
    private Integer subCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeptDto deptDto = (DeptDto) o;
        return Objects.equals(id, deptDto.id) &&
                Objects.equals(name, deptDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
