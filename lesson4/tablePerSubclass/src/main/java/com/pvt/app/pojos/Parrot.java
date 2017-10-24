package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Parrot implements Mammal {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;

    @Column
    private Integer age;

    @Column
    private String name;
}
