package com.pvt.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @OrderBy("summary")
    private List<Article> articles = new ArrayList<>();
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @org.hibernate.annotations.OrderBy(clause = "CHAR_LENGTH(title) DESC")
    private List<Book> books = new ArrayList<>();
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @OrderColumn(name = "ORDER_ID")
//    @OrderBy("order_id desc")
    private List<Pamphlet> pamphlets = new ArrayList<>();

    public Author (String name) {
        this.name = name;
    }

}
