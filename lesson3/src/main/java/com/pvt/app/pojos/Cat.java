package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Cat {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @Access(AccessType.FIELD)
    private Integer age;

    @Column
    @Access(AccessType.PROPERTY)
    private String name;

    @Embedded
//    @AttributeOverrides( value = {@AttributeOverride(name = "firstVisit")})
    private VetVisit vetVisit;
}
