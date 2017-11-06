package com.pvt.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @NaturalId
    @Column(name = "code", unique = true)
    private String code;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<Book> books = new ArrayList<>();

    public Author (String name) {
        this.name = name;
    }

}
