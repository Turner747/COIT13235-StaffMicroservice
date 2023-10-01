package com.optimed.staffmicroservice.response;

import com.optimed.staffmicroservice.model.Staff;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
public class ShiftResponse {
    private long id;
    private Staff staff;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;
}
