package com.optimed.StaffMicroservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private long staffId;

    private String salutation;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    @Column(name = "insert_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "staff_id", nullable = true)
//    private Staff manager;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
