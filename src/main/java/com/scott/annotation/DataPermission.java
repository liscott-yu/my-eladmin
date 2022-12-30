package com.scott.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project name  my-eladmin-backend1
 * filename  DataPermission
 * @author liscott
 * @date 2022/12/30 11:37
 * description  数据权限 注解
 * * 用于判断是否过滤数据权限
 *  * 1、如果没有用到 @OneToOne 这种关联关系，只需要填写 fieldName [参考：DeptQueryCriteria.class]
 *  * 2、如果用到了 @OneToOne ，fieldName 和 joinName 都需要填写，拿 UserQueryCriteria.class举例:
 *  * 应该是 @DataPermission(joinName = "dept", fieldName = "id")
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {

    /**
     * Entity 中的 字段名称
     */
    String fieldName() default "";

    /**
     * Entity 中与部门关联的字段名称
     */
    String joinName() default "";
}
