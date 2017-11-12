package com.pvt.app.beans;

import org.springframework.stereotype.Service;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */

@Service("performer")
public class PerformerImpl{
    public void print(){
        System.out.println(doSmth());
    }

    public String doSmth(){
        return "Performer is working...";
    }

    public void doSmthElse(){
        print();
    }
}
