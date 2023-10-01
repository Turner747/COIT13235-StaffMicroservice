package com.optimed.staffmicroservice.model;

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
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
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
    @Column(name = "provider_number")
    private String providerNumber;
    @Column(name = "prescriber_number")
    private String prescriberNumber;
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
