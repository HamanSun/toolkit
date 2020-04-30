package org.relaxation.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program RedisAspect
 * @description: TODO
 * @author: jjsunw
 * @create: 2020/4/29
 **/
@Aspect
@Component
public class RedisLockAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(org.relaxation.common.annotation.RedisLock)")
    public void redisPointcut(){}

}
