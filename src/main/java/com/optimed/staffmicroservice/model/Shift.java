package com.optimed.staffmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shift")
public class Shift implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @JsonManagedReference
    private Staff staff;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_time")
    private Date finishTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date", updatable=false)
    @CreationTimestamp
    @JsonIgnore
    private Date insertDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    @UpdateTimestamp
    @JsonIgnore
    private Date updateDate;
//    Collection<Appointment> appointments;

    @PrePersist
    @PreUpdate
    public void validateScheduleTime() {
    }
}
