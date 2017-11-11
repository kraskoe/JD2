package com.pvt.app;

import com.pvt.app.beans.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

//Почему он подтягивает бин из BaseCDPlayerConfig???
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DVDPlayerConfig.class)
public class annotationConfigIncludeTest {

    @Autowired
    private CompactDisc cd;

    @Autowired
    private MediaPlayer player;

    @Test
    public void runDVDPlayerTest(){
        player.play();
    }

//    @Test
//    public void runDVDPlayerTest(){
//        ApplicationContext context = new AnnotationConfigApplicationContext(DVDPlayerConfig.class);
//        MediaPlayer dvdPlayer = context.getBean(CDPlayer.class);
//        dvdPlayer.play();
//    }
}
