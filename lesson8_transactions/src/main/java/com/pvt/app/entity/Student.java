package com.pvt.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@OptimisticLocking(type = OptimisticLockType.VERSION)
@DynamicUpdate
@Table(name = "LESSON8_STUDENT")
public class Student {
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
    @OptimisticLock(excluded = true)
    private Integer numberOfLikes;
    @Version
//    @Generated(GenerationTime.ALWAYS)
//    @Source(value = SourceType.DB)
//    @Source(value = SourceType.VM)
    private Integer version;
//    private Date version;
}
