package com.optimed.staffmicroservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<Staff> staff;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }
}
