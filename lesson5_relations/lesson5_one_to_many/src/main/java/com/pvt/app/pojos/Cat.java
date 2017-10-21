package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LESSON5_OTM_CAT")
public class Cat {
    @Id
    @Column(name = "ANIMAL_ID", unique = true, nullable = false)
    @GeneratedValue(generator = "oto")
    @GenericGenerator(name = "oto",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "animal"))
    private Long id;

    @Column
    private Double jumpHeight;

    @Column
    private Boolean canCatchMice;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Animal animal;
}
