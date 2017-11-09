package com.pvt.app.beansBy;

import com.pvt.app.beans.CompactDisc;
import com.pvt.app.beans.MediaPlayer;
import lombok.Data;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
public class VCDPlayer implements MediaPlayer {
    private CompactDisc dvd;
    public void play(){
        dvd.play();
    }
}
