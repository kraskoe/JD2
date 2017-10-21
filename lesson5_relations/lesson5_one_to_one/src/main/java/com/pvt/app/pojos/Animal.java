package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Created by Yauheni Krasko on 20.10.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LESSON5_OTO_ANIMAL")
public class Animal {
    @Id
    @GeneratedValue
    @Column(name = "ANIMAL_ID")
    private Long id;
    @Column(name = "ANIMAL_AGE")
    private Integer age;
    @Column(name = "MONIKER")
    private String name;
    @OneToOne(mappedBy = "animal", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL)
    private Cat cat;
}
