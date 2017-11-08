package com.pvt.app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Yauheni Krasko on 08.11.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SomeField {
    private Long id;
    private String someField;
    private Integer nullField;

    public SomeField(String field, Integer nullField, String cField){
        this.someField = field;
        this.nullField = nullField;
    }
}
