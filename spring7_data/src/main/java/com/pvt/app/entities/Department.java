package com.pvt.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"employees"})
@Entity
public class Department {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="department",
            cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
}
