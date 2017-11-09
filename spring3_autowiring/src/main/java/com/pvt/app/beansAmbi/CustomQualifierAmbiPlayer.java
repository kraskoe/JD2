package com.pvt.app.beansAmbi;

import com.pvt.app.beans.CompactDisc;
import com.pvt.app.beans.MediaPlayer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
public class CustomQualifierAmbiPlayer implements MediaPlayer {
    @Autowired
    @CustomQualifier
    private CompactDisc cd;
    public void play(){
        cd.play();
    }
}
