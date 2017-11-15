package com.pvt.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data @ToString(exclude = {"department"})
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Employee {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;
    @ManyToOne
    private Department department;
}

