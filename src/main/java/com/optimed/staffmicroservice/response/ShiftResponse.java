package com.optimed.staffmicroservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.optimed.staffmicroservice.model.Staff;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class ShiftResponse {
    private long id;
    @JsonProperty("staff")
    private Staff staff;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date finishTime;
}
