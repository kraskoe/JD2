package com.pvt.app.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "LESSON4_TPSC_CAT")
@Polymorphism(type = PolymorphismType.EXPLICIT)
@PrimaryKeyJoinColumn(name = "ANIMAL_ID")
public class Cat extends Animal {
//public class Cat extends Animal implements Mammal{
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
