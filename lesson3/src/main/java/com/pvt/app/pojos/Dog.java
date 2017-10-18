package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Dog {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column
    @Access(AccessType.FIELD)
    private Integer age;

    @Column
    @Access(AccessType.PROPERTY)
    private String name;
    @CreationTimestamp
    private Date time;
}
