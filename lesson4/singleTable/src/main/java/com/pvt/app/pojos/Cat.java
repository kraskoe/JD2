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
@DiscriminatorValue("C")
public class Cat extends Animal {
    @Column(name = "CAT_JUMPING_HEIGHT")
    private Double jumpHeight;

    @Column(name = "CAT_CATCH_MICE_ABILITY")
    private Boolean canCatchMice;

    public Cat(Long id, Integer age, String name, Double height, Boolean catchMice) {
        super(id, age, name);
        this.jumpHeight = height;
        this.canCatchMice = catchMice;
    }
}
