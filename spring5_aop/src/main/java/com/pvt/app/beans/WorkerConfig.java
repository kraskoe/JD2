package com.pvt.app.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Yauheni Krasko on 12.11.2017.
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class WorkerConfig {
    @Bean
    public Aspect aspect(){
        return new Aspect();
    }
}
