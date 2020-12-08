package org.example.service;

import org.example.annotation.MyAnnotation;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class Calculator {

    private Calculator proxy;

    @MyAnnotation
    public int fib(int x) {
        if (x == 1 || x == 2) return 1;
        if (proxy == null)
            proxy = (Calculator) AopContext.currentProxy();
        return proxy.fib(x - 1 ) + proxy.fib(x - 2);
    }

}
