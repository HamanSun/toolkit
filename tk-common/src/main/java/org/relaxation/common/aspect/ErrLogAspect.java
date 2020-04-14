package org.relaxation.common.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.relaxation.common.annotation.ErrLog;
import org.relaxation.common.exception.ErrLogException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program ErrLogAspect
 * @description: 后台错误日志切面
 * @author: jjsunw
 * @create: 2020/4/14
 **/
@Aspect
@Component
@Slf4j
public class ErrLogAspect {
    @Pointcut("@annotation(org.relaxation.common.annotation.ErrLog)")
    public void errLogPointCut() {
    }

    /**
     * 从声明注解及方法抛出的自定义异常中获取到的异常信息
     *
     * @param point
     * @param e
     * @throws NoSuchMethodException
     */
    @AfterThrowing(pointcut = "errLogPointCut()", throwing = "e")
    public void errlog(JoinPoint point, Throwable e) throws NoSuchMethodException {
        JSONObject ret = new JSONObject();
        ErrLog errLog = getDeclaredAnnotation(point);
        ret.put("module", errLog.module());
        ret.put("opttype", errLog.opttype());
        ret.put("optdesc", errLog.optdesc());
        if (e instanceof ErrLogException) {
            ErrLogException exp = (ErrLogException) e;
            ret.put("optuserid", exp.getOptuserid());
            ret.put("errmsg", exp.getErrmsg());
            ret.put("opttime", exp.getOpttime());
            log.info("操作员 %s 在 %s 模块，进行 %s 操作时，发生异常！ 方法体抛出的异常信息是 %s", exp.getOptuserid(), errLog.module(), errLog.opttype(), exp.getErrmsg());
        }

    }

    /**
     * 获取方法中声明的注解
     *
     * @param point
     * @return
     * @throws NoSuchMethodException
     */
    private ErrLog getDeclaredAnnotation(JoinPoint point) throws NoSuchMethodException {
        // 获取方法名
        String methodName = point.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = point.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        ErrLog annotation = objMethod.getDeclaredAnnotation(ErrLog.class);
        // 返回
        return annotation;
    }
}
