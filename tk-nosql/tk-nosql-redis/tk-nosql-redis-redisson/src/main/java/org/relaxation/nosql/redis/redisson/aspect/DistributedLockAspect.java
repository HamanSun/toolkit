package org.relaxation.nosql.redis.redisson.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.relaxation.nosql.redis.redisson.config.RedissonLockOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program DistributedLockAspect
 * @description: 分布式锁切面
 * @author: jjsunw
 * @create: 2020/5/7
 **/
@Slf4j
@Aspect
@Component
public class DistributedLockAspect {
    @Autowired
    private RedissonLockOps redissonLockOps;

    @Around("@annotation(distributedLock)")
    public void around(ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock) {
        String key = distributedLock.value();
        int expire = distributedLock.expire();
        try {
            if (redissonLockOps.lock(key, expire)) {
                log.info("取锁成功");
                proceedingJoinPoint.proceed();
            } else {
                log.error("取锁失败");
            }
        } catch (Throwable t) {
            log.error("业务处理异常");
        } finally {
            redissonLockOps.unlock(key);
            log.info("锁已经释放");
        }
    }
}
