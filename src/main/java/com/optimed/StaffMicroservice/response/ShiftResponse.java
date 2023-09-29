package com.optimed.StaffMicroservice.response;

import com.optimed.StaffMicroservice.model.Staff;
import jakarta.persistence.*;

import java.util.Date;

public class ShiftResponse {
    private long id;
    private Staff staff;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;
}
