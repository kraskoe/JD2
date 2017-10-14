package com.pvt.app.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VetVisits {
    @Column (updatable = false)
    private Date firstVisit;
    @Column (insertable = false)
    private Date lastVisit;
}
