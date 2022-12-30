package com.scott.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * project name  my-eladmin-backend1
 * filename  EntityExistException
 * @author liscott
 * @date 2022/12/30 13:36
 * description  TODO
 */
public class EntityExistException extends RuntimeException {
    public EntityExistException (Class clazz, String field, String val){
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity) + " with "
                + field + " " + val + " existed";
    }
}
