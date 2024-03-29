package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    //setup logger
    private Logger myLogger=Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        //display method we are calling
        String theMethod=joinPoint.getSignature().toShortString();
        myLogger.info("====>> in @Before: calling method: "+ theMethod);

        //display the arguments to the method

        //get the arguments
        Object[] args=joinPoint.getArgs();

        //loop thru and display args
        for(Object object: args){
            myLogger.info("====>> argument: "+ object);
        }

    }

    //add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        //display method we are returning from
        String theMethod=joinPoint.getSignature().toShortString();
        myLogger.info("====>> in @AfterReturning: from method: "+ theMethod);

        //display data returned
        myLogger.info("====>> result: "+result);
    }
}
