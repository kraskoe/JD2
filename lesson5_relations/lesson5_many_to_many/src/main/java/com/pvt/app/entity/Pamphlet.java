package com.pvt.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pamphlet {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "PAMPHLET_TITLE")
    private String pTitle;
    @ManyToOne
    private Author author;
}
