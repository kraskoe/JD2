package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dog extends EntityDate {
    @Column
    private Integer age;

    @Column
    private String name;
}
