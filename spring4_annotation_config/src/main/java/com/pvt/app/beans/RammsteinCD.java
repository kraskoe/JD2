package com.pvt.app.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
//@Component("rammstein")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RammsteinCD implements CompactDisc {
    @Value("Rammstein")
    private String artist;
    @Value("Benzin")
    private String title;
    public void play(){
        System.out.println("Now playing: " + title + " by " + artist);
    }
}
