package com.scott.utils;

import cn.hutool.core.util.ObjectUtil;
import com.scott.exception.BadRequestException;

/**
 * project name  my-eladmin-backend1
 * filename  ValidationUtil
 * @author liscott
 * @date 2022/12/30 11:26
 * description  校验 工具类
 */
public class ValidationUtil {

    /**
     *  校验 null
     * @param obj  校验的对象
     * @param entity  实体
     * @param parameter  参数
     * @param value  值
     */
    public static void isNull(Object obj, String entity, String parameter, Object value) {
        if(ObjectUtil.isNull(obj)) {
            String msg = entity + " not exist " + parameter + " is " + value;
            throw new BadRequestException(msg);
        }
    }
}
