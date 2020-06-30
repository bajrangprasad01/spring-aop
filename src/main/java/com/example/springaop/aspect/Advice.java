package com.example.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class Advice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before ("com.example.springaop.aspect.CommonPointCut.controllerCommonPoint() && args(..,request)")
    public void beforeController(JoinPoint joinPoint, HttpServletRequest request) {
//        logger.info("Controller call from {}", joinPoint);
        logger.info("Start Header Section of request ");
        logger.info("Request Type : " + request.getMethod());
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            logger.info("Header Name: " + headerName + " Header Value : " + headerValue);
        }
        logger.info("Request Path info :" + request.getServletPath());
        logger.info("End Header Section of request ");
    }

    @AfterReturning (value = "com.example.springaop.aspect.CommonPointCut.controllerCommonPoint()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After execution of Controller {} and data return {}", joinPoint, result);
    }

    @Around ("com.example.springaop.aspect.CommonPointCut.controllerCommonPoint()")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object value = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        logger.info("Start at {}, end at {}, time take {}ms", startTime, endTime, timeTaken);
        return value;
    }

    @Before ("com.example.springaop.aspect.CommonPointCut.serviceCommonPoint() || com.example.springaop.aspect"
            + ".CommonPointCut.controllerCommonPoint()")
    public void beforeCommon(JoinPoint joinPoint) {
        logger.info("Method is called {}", joinPoint);
    }
}
