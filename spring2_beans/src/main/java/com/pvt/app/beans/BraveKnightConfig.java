package com.pvt.app.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Configuration
public class BraveKnightConfig {
    @Bean
    public Quest quest(){
        return new DragonQuest(System.out);
    }

    @Bean
    public Knight knight(){
        return new BraveKnight(quest());
    }
}
