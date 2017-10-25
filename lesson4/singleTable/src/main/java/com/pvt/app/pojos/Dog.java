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
@EqualsAndHashCode
@Entity
@DiscriminatorValue("D")
//@AttributeOverrides({
//        @AttributeOverride(name = "name", column = @Column(name = "ANIMAL_MONIKER")),
//        @AttributeOverride(name = "age", column = @Column(name = "ANIMAL_AGE"))
//})
public class Dog extends Animal {
    @Column(name = "DOG_RUNNING_SPEED")
    private Double speed;

    @Column(name = "DOG_BREED")
    private String breed;

    public Dog(Long id, Integer age, String name, Double speed, String breed) {
        super(id, age, name);
        this.speed = speed;
        this.breed = breed;
    }
}
