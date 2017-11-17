package com.pvt.app;

import com.pvt.app.beans.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

//@ComponentScan(basePackageClasses = {CardigansCD.class}) не ограничивает автосвязывание только одним классом?
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class autowiringTest {
//    private static final Logger logger = LogManager.getLogger(autowiringTest.class);

    @Autowired
    private CompactDisc cd;

    @Autowired
    private MediaPlayer player;

    @Test
    public void notNullCDTest(){
        Assert.assertNotNull(cd);
    }

    @Test
    public void runCDTest(){
        cd.play();
    }

    @Test
    public void runCDPlayerTest(){
        player.play();
    }
}
