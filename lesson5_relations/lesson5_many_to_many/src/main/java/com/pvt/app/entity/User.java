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
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @ElementCollection
    @CollectionTable(name = "USER_PETS",
    joinColumns = @JoinColumn(name = "OWNER_ID"))
//    @OrderColumn(name = "`order`")
    private List<String> pets = new ArrayList<>();
}
