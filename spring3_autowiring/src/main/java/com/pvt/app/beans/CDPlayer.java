package com.pvt.app.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Component
public class CDPlayer implements MediaPlayer {
    private CompactDisc cd;
    @Autowired(required = false)
//    @Inject
    public CDPlayer(CompactDisc cd){
        this.cd = cd;
    }
//    @Autowired
    public void setCd(CompactDisc cd){
        this.cd = cd;
    }
//    @Autowired
    public void insertCd(CompactDisc cd){
        this.cd = cd;
    }

    public void play(){
        cd.play();
    }
}
