package com.pvt.app.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
@AutowiredCD
//@Component("cardigans")
public class CardigansCD implements CompactDisc {
    private String artist = "The Cardigans";
    private String title = "My Favourite Game";
    public void play(){
        System.out.println("Now playing: " + title + " by " + artist);
    }
}
