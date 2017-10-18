package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VetVisits {
    @Column (updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstVisit;
    @Column (insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastVisit;

    public VetVisits() {
        this.firstVisit = Calendar.getInstance().getTime();
        this.lastVisit = Calendar.getInstance().getTime();
    }
}
