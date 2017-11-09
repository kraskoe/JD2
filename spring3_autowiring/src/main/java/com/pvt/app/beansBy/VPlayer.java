package com.pvt.app.beansBy;

import com.pvt.app.beans.CompactDisc;
import com.pvt.app.beans.MediaPlayer;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */
public class VPlayer implements MediaPlayer {
    private CompactDisc dvd;
    public VPlayer(CompactDisc dvd){
        this.dvd = dvd;
    }
    public void play(){
        dvd.play();
    }
}
