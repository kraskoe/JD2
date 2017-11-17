package com.pvt.app.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
//@AutowiredCD
@Component
public class TerminatorDVD implements CompactDisc {
    private String artist = "A. Shvarzenegger";
    private String title = "Terminator antology";
    public void play(){
        System.out.println("Now playing: " + title + " by " + artist);
    }
}
