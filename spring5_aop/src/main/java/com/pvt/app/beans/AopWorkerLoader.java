package com.pvt.app.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni Krasko on 12.11.2017.
 */
public class AopWorkerLoader {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring_aop_annotation.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(WorkerConfig.class);
        Worker worker = (Worker) context.getBean("worker");
        System.out.println("<---------------------------------->");
        worker.doSmth();
        System.out.println("<---------------------------------->");
        worker.doSmthAround();
        System.out.println("<---------------------------------->");
        worker.doSmth("Rafshan");
        System.out.println("<---------------------------------->");
        worker.doSmth("Jamshut", 5);
        System.out.println("<---------------------------------->");
        worker.doSmthEx();
        ((ClassPathXmlApplicationContext) context).close();
    }
}
