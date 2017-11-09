package com.pvt.app.beansBy;

import com.pvt.app.beans.CompactDisc;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */
@Data
public class VideoCD implements CompactDisc {
    private String title;

    public void play(){
        System.out.println("Now playing: " + title );
    }
}
