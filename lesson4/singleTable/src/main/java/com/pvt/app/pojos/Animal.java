package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Yauheni Krasko on 20.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "LESSON4_ST_ANIMAL")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ANIMAL_TYPE", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("A")
public class Animal {
    @Id
    @GeneratedValue
    @Column(name = "ANIMAL_ID")
    private Long id;
    @Column(name = "ANIMAL_AGE")
    private Integer age;
    @Column(name = "MONIKER")
    private String name;
}
