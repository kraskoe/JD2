package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VetVisit {
    @Column (updatable = false)
    @CreationTimestamp
    private Date firstVisit;
    @Column (insertable = false)
    private Date lastVisit;
}
