package com.pvt.app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Yauheni Krasko on 08.11.2017.
 */

@Data
@AllArgsConstructor
public class SimpleClassForConstructor implements InitializingBean, DisposableBean {
    private String name;

    @Override
    public void afterPropertiesSet(){
        System.out.println("Properties were set");
    }

    @Override
    public void destroy(){
        System.out.println("Bean was destroyed");
    }
}
