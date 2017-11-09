package com.pvt.app;

import com.pvt.app.beans.MediaPlayer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */
public class autowireByTest {
    @Test
    public void autoWireByNameTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiringBy.xml");
        MediaPlayer player = (MediaPlayer) context.getBean("DVDPlayerByName");
        player.play();
    }

    @Test
    public void autoWireByTypeTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiringBy.xml");
        MediaPlayer player = (MediaPlayer) context.getBean("DVDPlayerByType");
        player.play();
    }

    @Test
    public void autoWireByConstructorTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiringBy.xml");
        MediaPlayer player = (MediaPlayer) context.getBean("DVDPlayerByConstructor");
        player.play();
    }
}
