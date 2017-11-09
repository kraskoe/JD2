package com.pvt.app.beansAmbi;

import com.pvt.app.beans.CompactDisc;
import lombok.Data;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
public class VidCD implements CompactDisc {
    private String title;

    public void play(){
        System.out.println("Now playing: " + title );
    }
}
