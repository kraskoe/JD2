package com.pvt.app;

import com.pvt.app.beansAmbi.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */
public class ambiDepTest {
    @Test
    public void autoWireAmbiDepTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiringAmbi.xml");
        FirstAmbiPlayer player1 = context.getBean(FirstAmbiPlayer.class);
        player1.play();
        SecondAmbiPlayer player2 = context.getBean(SecondAmbiPlayer.class);
        player2.play();
        CustomQualifierAmbiPlayer player3 = context.getBean(CustomQualifierAmbiPlayer.class);
        player3.play();
        ValueAmbiPlayer player4 = context.getBean(ValueAmbiPlayer.class);
        player4.play();
        ResourceAmbiPlayer player5 = context.getBean(ResourceAmbiPlayer.class);
        player5.play();
    }
}
