package com.pvt.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LESSON6_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private Integer age;
    @Column
    private Double height;
//    @ElementCollection
//    @CollectionTable(name = "USER_PETS",
//    joinColumns = @JoinColumn(name = "OWNER_ID"))
////    @OrderColumn(name = "`order`")
//    private List<String> pets = new ArrayList<>();
}
