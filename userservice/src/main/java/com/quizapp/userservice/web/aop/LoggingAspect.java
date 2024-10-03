package com.quizapp.userservice.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.quizapp.userservice.service.UserService.*(..))")
    public void userServiceMethods() {}

    @Before("userServiceMethods()")
    public void logBeforeMethod() {
        log.info("Method in UserService is starting.");
    }

    @AfterReturning("userServiceMethods()")
    public void logAfterMethod() {
        log.info("Method in UserService has completed.");
    }
}
