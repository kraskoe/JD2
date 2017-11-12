package com.pvt.app.beans;

import org.springframework.stereotype.Service;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */

@Service("worker")
public class Worker{
    public void doSmth(){
        System.out.println("Worker is working...");
    }

    public void doSmthEx(){
        System.out.println("Worker throws exception:");
        throw new RuntimeException("Worker is drunken :(");
    }

    public void doSmthAround(){
        System.out.println("Worker is walking around...");
    }

    public void doSmth(String string){
        System.out.println(string);
    }

    public void doSmth(String string, Integer number){
        System.out.println(string + " " + number);
    }
}
