package com.pvt.app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Yauheni Krasko on 08.11.2017.
 */

@Data
@AllArgsConstructor
public class SimpleFactory {
    private String name;

    public SimpleFactory(){
        name = "Instance of simple factory";
    }

    public static SimpleFactory getSimpleFactoryWithStaticMethod(){
        return new SimpleFactory();
    }

    public SimpleClassForConstructor getInstanceWithOutStaticMethod(){
        return new SimpleClassForConstructor("This simple class was created by simple factory");
    }
}
