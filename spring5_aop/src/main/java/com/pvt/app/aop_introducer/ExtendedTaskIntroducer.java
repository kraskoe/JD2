package com.pvt.app.aop_introducer;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
public class ExtendedTaskIntroducer {
    @DeclareParents(value = "com.pvt.app.aop_introducer.DoTask+",
            defaultImpl = ExtendedTaskImpl.class)
    public static DoExtendedTask extendedTask;
}
