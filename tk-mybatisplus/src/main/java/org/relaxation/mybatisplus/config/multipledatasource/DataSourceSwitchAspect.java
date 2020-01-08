package org.relaxation.mybatisplus.config.multipledatasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program DataSourceSwitchAspect
 * @description: AOP实现的数据源切换
 * @author: jjsunw
 * @create: 2020/1/6
 **/
@Component
@Order(value = -100)
@Slf4j
@Aspect
public class DataSourceSwitchAspect {
    @Pointcut("execution(* org.relaxation.mybatisplus.dao.db1.*Mapper.*(..))")
    private void vprAspect() {
    }

    @Pointcut("execution(* org.relaxation.mybatisplus.dao.db2.*Mapper.*(..))")
    private void agentAspect() {
    }

    @Before("vprAspect()")
    public void vprDb() {
        log.info("切换到db1 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db1);
    }

    @Before("agentAspect()")
    public void agentDb() {
        log.info("切换到db2 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db2);
    }
}
