package com.quizapp.userservice.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ExceptionLoggingAspect {

    @AfterThrowing(pointcut = "execution(* com.quizapp.userservice.service.UserService.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        log.error("Exception thrown in UserService: " + exception.getMessage(), exception);
    }
}
