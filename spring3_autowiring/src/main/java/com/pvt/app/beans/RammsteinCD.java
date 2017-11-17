package com.pvt.app.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
//@Component
public class RammsteinCD implements CompactDisc {
    private String artist = "Rammstein";
    private String title = "Benzin";
    public void play(){
        System.out.println("Now playing: " + title + " by " + artist);
    }
}
