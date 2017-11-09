package com.pvt.app.beansAmbi;

import com.pvt.app.beans.CompactDisc;
import com.pvt.app.beans.MediaPlayer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
public class ResourceAmbiPlayer implements MediaPlayer {
    @Resource(name = "nazis")
    private CompactDisc cd;
    public void play(){
        cd.play();
    }
}
