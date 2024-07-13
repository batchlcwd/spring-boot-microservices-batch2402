package com.spring.boot.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogsAspect {

    //    advices : actual code
//
    @Before("execution(* com.spring.boot.aop.services.LoginService.new())")
    public void maintainLogs() throws Throwable {
        System.out.println("maintainLogs  executed: "+"Before");

    }

    @After("execution(* com.spring.boot.aop.services.*.*())")
    public void maintainLogsAfter() {
        System.out.println("maintainLogs  executed : "+"After");
    }
}
