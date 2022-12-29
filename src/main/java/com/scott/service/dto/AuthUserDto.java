package com.scott.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * project name  my-eladmin-backend1
 * filename  AuthUserDto
 *
 * @author liscott
 * @date 2022/12/28 17:19
 * description  登录用的AuthUserDto
 * 前端发过来的数据传输类，包含用户信息和验证码信息
 *
 */
@Setter
@Getter
public class AuthUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";
}
