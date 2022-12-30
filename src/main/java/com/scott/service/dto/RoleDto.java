package com.scott.service.dto;

import com.scott.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  RoleDto
 * @author liscott
 * @date 2022/12/30 13:51
 * description  TODO
 */
@Setter
@Getter
public class RoleDto extends BaseDTO implements Serializable {
    private Long id;
    private Set<MenuDto> menus;
    private Set<DeptDto> depts;
    private String name;
    private String dataScope;
    private Integer level;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
