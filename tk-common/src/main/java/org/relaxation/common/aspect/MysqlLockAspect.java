package org.relaxation.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.relaxation.common.annotation.MysqlLock;
import org.relaxation.common.lock.DistributedLockOps;
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
public class MysqlLockAspect {
    @Autowired
    private DistributedLockOps distributedLockOps;

    @Around("@annotation(mysqlLock)")
    public void around(ProceedingJoinPoint proceedingJoinPoint, MysqlLock mysqlLock) {
        String methodName = mysqlLock.methodName();
        try {
            if (distributedLockOps.mysqlLock(methodName)) {
                log.info("mysql锁获取成功");
                proceedingJoinPoint.proceed();
            } else {
                log.error("mysql锁获取失败");
            }
        } catch (Throwable t) {
            log.error("业务处理异常:{}",t.getMessage());
        } finally {
            distributedLockOps.mysqlUnLock(methodName);
            log.info("mysql锁释放");
        }
    }
}
