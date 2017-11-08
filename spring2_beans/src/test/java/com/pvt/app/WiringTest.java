package com.pvt.app;

import com.pvt.app.beans.ExpressionBean;
import com.pvt.app.beans.SimpleClassForConstructor;
import com.pvt.app.beans.SimpleFactory;
import com.pvt.app.beans.Something;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */
public class WiringTest {

    //    Почему afterPropertiesSet вызывается до получения инстанса factory если этот метод вызывается в другом классе позже?
//    Почему в обоих тестах вызываются из разных бинов APS/init/destroy методы???
    @Test
    public void factoryTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
        SimpleFactory simpleFactory = (SimpleFactory) context.getBean("simpleFactory");
        System.out.println(simpleFactory);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        SimpleClassForConstructor simple1 = (SimpleClassForConstructor) context.getBean("simpleClassFromSimpleFactory");
        System.out.println(simple1);
        SimpleClassForConstructor simple2 = (SimpleClassForConstructor) context.getBean("simpleClassBean");
        System.out.println(simple2);
        ((ClassPathXmlApplicationContext) context).close();
    }

    @Test
    public void beanConfigurationTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
//        SomeField someField = (SomeField) context.getBean("someFieldBean");
        Something something = (Something) context.getBean("somethingBean");
        System.out.println(something);
        System.out.println(something.getSomeName());
        something.setSomeName("Something has no Name");
        System.out.println(something.getSomeName());
        ((ClassPathXmlApplicationContext) context).close();
    }

    @Test
    public void elTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
        ExpressionBean expressionBean = (ExpressionBean) context.getBean("expressionBean");
        System.out.println(expressionBean);
    }
}
