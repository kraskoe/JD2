package com.pvt.app.beans;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.logging.Logger;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */
public class Aspect {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Aspect.class);

    public void before(){
        logger.error("Before method...");
    }

    public void after(){
        logger.error("After method...");
    }

    public void afterReturning(){
        logger.error("After returning...");
    }

    public void afterThrowing(){
        logger.error("After throwing...");
    }

    public void around(ProceedingJoinPoint joinPoint){
        try {
            before();
            joinPoint.proceed();
            after();
            afterReturning();
        } catch (Throwable throwable) {
            afterThrowing();
        }
    }
}
