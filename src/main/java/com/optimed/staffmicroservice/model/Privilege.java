package com.optimed.staffmicroservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "privilege")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    @JsonBackReference
    private Collection<Role> roles;

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

    public Privilege(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Privilege(id=" + getId() + ", name=" + getName() + ")";
    }
}

