package com.pvt.app.main;

import com.pvt.app.beans.Performer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */
public class MainClass {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
        Performer performer = (Performer)context.getBean("performerBean");
        performer.print();
    }
}
