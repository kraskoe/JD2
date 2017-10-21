package com.pvt.app.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "LESSON4_TPC_CAT")
public class Cat extends Animal {
    @Column
    private Double jumpHeight;

    @Column
    private Boolean canCatchMice;

    public Cat(Long id, Integer age, String name, Double height, Boolean catchMice) {
        super(id, age, name);
        this.jumpHeight = height;
        this.canCatchMice = catchMice;
    }
}
