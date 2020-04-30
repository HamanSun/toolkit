package org.relaxation.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    /**
     * 业务key
     *
     * @return
     */
    String bizKey() default "";

    /**
     * key 超时时间设置，可以定义默认值
     *
     * @return
     */
    int expire() default 10000;

    /**
     * 给客户端的提示信息
     *
     * @return
     */
    String tip() default "获取锁失败，请稍后重试";
}
