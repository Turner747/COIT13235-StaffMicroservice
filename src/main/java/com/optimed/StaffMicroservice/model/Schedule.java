package com.optimed.StaffMicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "schedule_id")
    private long scheduleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    private Staff staffId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;

    @PrePersist
    @PreUpdate
    public void validateScheduleTime() {
    }
}
