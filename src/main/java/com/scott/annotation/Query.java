package com.scott.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project name  my-eladmin-backend1
 * filename  Query
 * @author liscott
 * @date 2022/12/30 10:16
 * description  查询 注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    // 基本对象的 属性名
    String propName() default  "";
    // 查询方式
    Type type() default Type.EQUAL;
    // 连接查询的属性名，如 User 类中的 dept
    String joinName() default "";
    // 连接方式，默认 LEFT
    Join join() default Join.LEFT;
    // 多字段模糊查询，只支持 String，多个用 逗号 分开,
    // 如： @Query(blurry = "email.username")
    String blurry() default "";

    enum Type {
        // 相等
        EQUAL,
        //大于
        GREATER_THAN,
        //包含
        IN
    }
    /**
     * 适用于简单连接查询，复杂的请自定义该注解，或者使用sql查询
     */
    enum Join {
        LEFT, RIGHT, INNER
    }
}
