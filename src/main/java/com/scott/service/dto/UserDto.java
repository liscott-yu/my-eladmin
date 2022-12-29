package com.scott.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scott.base.BaseDTO;
import com.scott.service.dto.small.DeptSmallDto;
import com.scott.service.dto.small.JobSmallDto;
import com.scott.service.dto.small.RoleSmallDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * project name  my-eladmin-backend1
 * filename  UserDto
 * @author liscott
 * @date 2022/12/28 18:02
 * description  TODO
 */
@Setter
@Getter
public class UserDto extends BaseDTO implements Serializable {
    private Long id;

    private Set<RoleSmallDto> roles;

    private Set<JobSmallDto> jobs;

    private DeptSmallDto dept;

    private Long deptId;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    @JsonIgnore
    private String password;

    private Boolean enabled;

    @JsonIgnore
    private Boolean isAdmin = false;

    private Date pwdResetTime;
}
