package org.relaxation.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    /**
     * 锁名称
     *
     * @return
     */
    String methodName() default "REDIS_DISTRIBUTED_LOCK";

    /**
     * 默认过期时间(单位：秒)
     *
     * @return
     */
    int expire() default 10;

    /**
     * 重试次数，-1表示无限重试
     *
     * @return
     */
    int retryTimes() default -1;
}
