package com.pvt.app.beans;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */

@org.aspectj.lang.annotation.Aspect
public class Aspect {
//    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Aspect.class);

    @Pointcut("execution(* com.pvt.app.beans.Worker.doSmth())")
    public void doWork() {}

    @Pointcut("execution(* com.pvt.app.beans.Worker.doSmthEx(..))")
    public void doWorkEx() {}

    @Pointcut("execution(* com.pvt.app.beans.Worker.doSmthAround(..))")
    public void doWorkAround() {}

    @Pointcut("execution(* com.pvt.app.beans.Worker.doSmth(String)) && args(name)")
    public void doWorkArg(String name) {}

    @Pointcut("execution(* com.pvt.app.beans.Worker.doSmth(String, Integer)) && args(name, i)")
    public void doWorkArgs(String name, Integer i) {}

//    @Before("execution(* com.pvt.app.beans.Worker.doSmth())")
    @Before("doWork()")
    public void before(){
        System.out.println("Before method...");
//        logger.error("Before method...");
    }

    @After("doWork()")
    public void after(){
        System.out.println("After method...");
//        logger.error("After method...");
    }

    @AfterReturning("doWork()")
    public void afterReturning(){
        System.out.println("After returning...");
//        logger.error("After returning...");
    }

    @AfterThrowing("doWorkEx()")
    public void afterThrowing(){
        System.out.println("After throwing...");
//        logger.error("After throwing...");
    }

    @Around("doWorkAround()")
    public void around(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("Worker looks at the work...");
            joinPoint.proceed();
            System.out.println("Worker sighs...");
        } catch (Throwable throwable) {
            afterThrowing();
        }
    }

    @Before("doWorkArg(string)")
    public void beforeArg(String string){
        System.out.println("Before method with arg: " + string);
//        logger.error("Before method with arg: " + string);
    }

    @Before("doWorkArgs(string, integer)")
    public void beforeArg(String string, Integer integer){
        System.out.println("Before method with arg: " + string + " " + integer);
//        logger.error("Before method with arg: " + string + " " + integer);
    }
}
