package org.relaxation.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program ErrLog
 * @description: 自定义注解
 * @author: jjsunw
 * @create: 2020/4/14
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MysqlLock {
    /**
     * 方法名称
     * @return
     */
    String methodName() default "";
}
