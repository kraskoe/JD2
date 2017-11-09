package com.pvt.app.beans;

import java.io.PrintStream;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */
public class DragonQuest implements Quest {
    private PrintStream stream;

    public DragonQuest (PrintStream ps){
        stream = ps;
    }

    public void embark(){
        stream.println("The dragon quest begins!");
    }
}
