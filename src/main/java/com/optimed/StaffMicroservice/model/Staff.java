package com.optimed.StaffMicroservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff")
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String street;
    private String suburb;
    private String state;
    private int postcode;
    private String phone;
    @Column(unique = true, nullable = false)
    private String email;
    @JsonIgnore
    private String password;
    private String provider_number;
    private String prescriber_number;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable=false)
    @CreationTimestamp
    @JsonIgnore
    private Date insert_date;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonIgnore
    private Date updated_date;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<Shift> shifts;

//    private Collection<VisitNote> visit_notes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinTable(
            name = "staff_roles",
            joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;
}
