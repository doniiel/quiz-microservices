package com.quizapp.userservice.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.quizapp.userservice.service.UserService.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joint) throws Throwable{
        long start = System.currentTimeMillis();
        Object proceed = joint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joint.getSignature() + " executed in " + executionTime + " ms");
        return proceed;
    }
}
