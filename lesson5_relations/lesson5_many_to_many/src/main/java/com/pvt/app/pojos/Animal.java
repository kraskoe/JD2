package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni Krasko on 20.10.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LESSON5_MTM_ANIMAL")
public class Animal {
    @Id
    @GeneratedValue
    @Column(name = "ANIMAL_ID")
    private Long id;
    @Column(name = "ANIMAL_AGE")
    private Integer age;
    @Column(name = "MONIKER")
    private String name;
//    @OneToOne(mappedBy = "animal", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL)
    private Cat cat;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ZOO_ID")
    private Zoo zoo;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ANIMAL_PROCEDURE", joinColumns = {@JoinColumn(name = "ANIMAL_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PROCEDURE_ID")})
    private List<Procedure> procedures = new ArrayList<>();

}
