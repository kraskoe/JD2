package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LESSON5_MTM_PROCEDURE")
public class Procedure {
    @Id
    @GeneratedValue
    @Column(name = "PROCEDURE_ID")
    private Long id;
    @Column(name = "PROCEDURE")
    private String name;
//    @CreationTimestamp
//    @Column(name = "DATE")
//    private LocalDateTime startDate;
    @ManyToMany(mappedBy = "procedures", cascade = CascadeType.ALL)
    private List<Animal> animals = new ArrayList<>();

    public Procedure(String name) {
        this.name = name;
    }

}
