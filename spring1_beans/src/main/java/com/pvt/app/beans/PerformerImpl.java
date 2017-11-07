package com.pvt.app.beans;

/**
 * Created by Yauheni Krasko on 07.11.2017.
 */
public class PerformerImpl implements Performer {
    @Override
    public void print(){
        System.out.println(doSmth());
    }

    @Override
    public String doSmth(){
        return "Performer is working...";
    }

    @Override
    public void doSmthElse(){
        print();
    }
}
