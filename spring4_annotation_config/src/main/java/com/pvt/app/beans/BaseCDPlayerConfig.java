package com.pvt.app.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Yauheni Krasko on 11.11.2017.
 */

@Configuration
public class BaseCDPlayerConfig {
    @Bean
    public CompactDisc cd (){
        return new CardigansCD();
    }
}
