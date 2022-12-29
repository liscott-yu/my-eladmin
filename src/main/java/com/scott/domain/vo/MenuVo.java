package com.scott.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * project name  my-eladmin-backend1
 * filename  MenuVo
 *
 * @author liscott
 * @date 2022/12/29 11:30
 * @JsonInclude(JsonInclude.Include.NON_EMPTY)
 * //这个注解在类上方，表示类的某个属性如果为空字符串或者null的时候，则该属性不参与序列化
 * description  TODO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVo implements Serializable {

    private String name;
    private String path;
    private Boolean hidden;
    private String redirect;
    private String component;
    private Boolean alwaysShow;
    private MenuMetaVo meta;
    private List<MenuVo> children;
}
