package com.pvt.app.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CDPlayer implements MediaPlayer {
//    @Autowired
//    @AutowiredCD
    private CompactDisc cd;
    public CDPlayer(CompactDisc cd){
        this.cd = cd;
    }
    public void play(){
        cd.play();
    }
}
