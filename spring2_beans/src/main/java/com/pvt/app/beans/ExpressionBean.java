package com.pvt.app.beans;

/**
 * Created by Yauheni Krasko on 08.11.2017.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionBean {
    private Double pi;
    private Double multiplier;
    private Integer count = 7;
    private String email = "krasko@tut.by";
    private Boolean logic;
    private String elvis;
    private Boolean regexp;
    private String name;

    private Collection<String> names;

    @Override
    public String toString() {
        return "ExpressionBean{" +
                "multiplier=" + multiplier +
                ", pi=" + pi +
                ", logic=" + logic +
                ", elvis='" + elvis + '\'' +
                ", regexp=" + regexp +
                ", name=" + name +
                ", names=" + names +
                '}';
    }
}
