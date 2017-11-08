package com.pvt.app;

import com.pvt.app.beans.Performer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */
public class BeansTest {
//    Не перехватываются вызовы методов внутри методов???
    @Test
    public void beanBeforeAfterTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
        Performer performer = (Performer)context.getBean("performerBean");
        performer.print();
        ((ClassPathXmlApplicationContext)context).close();
    }

    @Test
    public void beanThrowReturnTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
        Performer worker = (Performer) context.getBean("workerBean");
        worker.print();
        ((ClassPathXmlApplicationContext)context).close();
    }

    @Test
    public void beanAroundTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
        Performer performer = (Performer)context.getBean("performerBean");
        performer.doSmthElse();
        ((ClassPathXmlApplicationContext)context).close();
    }
}
