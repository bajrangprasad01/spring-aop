package com.example.springaop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {
    @Pointcut("execution(* com.example.springaop.controller.*.*(..))")
    public void controllerCommonPoint() {}

    @Pointcut("execution(* com.example.springaop.service.*.*(..))")
    public void serviceCommonPoint() {}

    @Pointcut("execution(* com.example.springaop.dao.*.*(..))")
    public void daoCommonPoint() {}
}
