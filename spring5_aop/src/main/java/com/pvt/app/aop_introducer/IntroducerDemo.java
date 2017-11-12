package com.pvt.app.aop_introducer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntroducerDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aspects_introducer.xml");
        DoTask doTask = context.getBean("taskService", DoTask.class);
        doTask.doMainJob();
        ((DoExtendedTask)doTask).doExtraJob();
        context.close();
    }
}
