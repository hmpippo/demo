package org.example.annotation;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.utils.DBContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* org.example.service.impl.*.*(..))")
    public void pointCut() {}


    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        if (method != null && method.isAnnotationPresent(Master.class)) {
            DBContextHolder.master();
            return;
        }

        String methodName = joinPoint.getSignature().getName();
        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
            DBContextHolder.slave();
        } else {
            DBContextHolder.master();
        }
    }

    @After("pointCut()")
    public void after() {
        DBContextHolder.clear();
    }
}
