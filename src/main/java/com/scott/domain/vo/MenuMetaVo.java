package com.scott.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * project name  my-eladmin-backend1
 * filename  MenuMetaVo
 * @author liscott
 * @date 2022/12/29 11:28
 * description  TODO
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {
    private String title;
    private String icon;
    private Boolean noCache;
}
