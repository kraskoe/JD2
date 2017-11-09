package com.pvt.app.beans;

import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Component("cardigans")
//@Named("cardigans")
public class CardigansCD implements CompactDisc {
    private String artist = "The Cardigans";
    private String title = "My Favourite Game";
    public void play(){
        System.out.println("Now playing: " + title + " by " + artist);
    }
}
