package com.pvt.app.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by Yauheni Krasko on 20.10.2017.
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class EntityDate {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationTime;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updationTime;
}
