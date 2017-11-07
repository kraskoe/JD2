package com.pvt.app.beans;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */
public class Worker implements Performer {
    @Override
    public void print(){
        System.out.println(doSmth());
    }

    @Override
    public String doSmth(){
        if(Math.random() < 0.5)
            throw new RuntimeException("Worker exception");
        return "Worker is working...";
    }

    @Override
    public void doSmthElse(){
        print();
    }
}
