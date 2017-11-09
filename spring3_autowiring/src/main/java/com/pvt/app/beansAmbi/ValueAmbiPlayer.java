package com.pvt.app.beansAmbi;

import com.pvt.app.beans.CompactDisc;
import com.pvt.app.beans.MediaPlayer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
public class ValueAmbiPlayer implements MediaPlayer {
    @Value("#{zombie}")
    private CompactDisc cd;
    public void play(){
        cd.play();
    }
}
