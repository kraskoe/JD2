package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yauheni Krasko on 21.10.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LESSON5_ZOO")
public class Zoo {
    @Id
    @GeneratedValue
    @Column(name = "ZOO_ID")
    private Long id;
    @Column(name = "ZOO_NAME")
    private String name;
    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL)
    private List<Animal> animals;

}
