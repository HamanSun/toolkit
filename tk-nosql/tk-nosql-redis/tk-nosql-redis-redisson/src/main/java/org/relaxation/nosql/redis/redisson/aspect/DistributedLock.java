package org.relaxation.nosql.redis.redisson.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program DistributedLock
 * @description: 分布式锁自定义注解
 * @author: jjsunw
 * @create: 2020/5/7
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    /**
     * 锁的名称，规则： 模块名-方法名-lock
     *
     * @return
     */
    String value() default "default-redisson-lock";

    /**
     * 过期时间，默认设置为10秒
     *
     * @return
     */
    int expire() default 10;
}
