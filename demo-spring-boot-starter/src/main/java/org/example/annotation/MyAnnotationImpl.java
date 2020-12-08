package org.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class MyAnnotationImpl {

    private final Map<Integer, Integer> cache = new HashMap<>();

    @Pointcut("execution(* org.example.service.Calculator.*(..))")
    private void cut() {
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret = null;

        int args = (int) joinPoint.getArgs()[0];
        Integer val = cache.get(args);
        if (val != null) {
            log.info("hit cache: {}->{}", args, val);
            return val;
        }

        try {
            log.info("start method invoke: {}, args: {}", joinPoint.getSignature().getName(),
                    joinPoint.getArgs()[0]);
            ret = joinPoint.proceed();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("end method invoke: {}, args: {}", joinPoint.getSignature().getName(),
                joinPoint.getArgs()[0]);

        cache.put(args, (Integer) ret);
        return ret;
    }

    @Before("cut()")
    public void before() {
    }

    @After("cut()")
    public void after() {
    }

}
