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
@Table(name = "LESSON4_TPC_DOG")
public class Dog extends Animal {
    @Column
    private Double speed;

    @Column
    private String breed;

    public Dog(Long id, Integer age, String name, Double speed, String breed) {
        super(id, age, name);
        this.speed = speed;
        this.breed = breed;
    }
}
