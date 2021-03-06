package com.pvt.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LESSON6_BOOK")
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @ManyToOne
    private Author author;
}
