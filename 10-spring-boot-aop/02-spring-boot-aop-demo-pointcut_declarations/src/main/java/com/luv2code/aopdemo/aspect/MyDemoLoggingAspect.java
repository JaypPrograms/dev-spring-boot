package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        String method=proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @Around on method: "+ method);

        long begin=System.currentTimeMillis();

        Object result =null;

        try{
            result=proceedingJoinPoint.proceed();
        }catch (Exception e){

            System.out.println(e.getMessage());

            throw e;
        }

        long end=System.currentTimeMillis();

        long duration =end-begin;

        System.out.println("\n====>Duration: "+duration/1000);

        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJointPoint){

        String method=theJointPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @After (finally) on method: "+ method);
    }

    @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJointPoint, Throwable theExc){

        String method=theJointPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @AfterThrowing on method: "+ method);

        System.out.println("\n====> The exception is: "+ theExc);

    }

    @AfterReturning(pointcut ="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
    returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJointPoint, List<Account> result){
        String method =theJointPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @AfterReturning on method: "+ method);
        System.out.println("\n====> result is: "+ result);

        convertAccountNamesToUpperCase(result);

        System.out.println("\n====> result is: "+ result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        for(Account theAccount: result){
            theAccount.setName(theAccount.getName().toUpperCase());
        }
    }


    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.createPointToExcludeGetterAndSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint){
        System.out.println("\n=====>>> Executing @before advice on addAccount()");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();

        System.out.println("Method: "+ methodSignature);

        //display method arguments

        //get args
        Object[] args = theJointPoint.getArgs();
        //loot thru args
        for(Object tempArg : args){
            System.out.println(tempArg);

            if(tempArg instanceof Boolean){
                boolean holder= (boolean) tempArg;
                System.out.println("hello ako si jayp "+ holder);
            }

            if(tempArg instanceof Account){
                Account theAccount=(Account) tempArg;
                System.out.println("Account name: "+ theAccount.getName());
                System.out.println("Account level: "+ theAccount.getLevel());
            }
        }
    }



}
