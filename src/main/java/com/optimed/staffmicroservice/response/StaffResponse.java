package com.optimed.staffmicroservice.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimed.staffmicroservice.model.Role;
import com.optimed.staffmicroservice.model.Shift;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class StaffResponse {
    private long id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String street;
    private String suburb;
    private String state;
    private int postcode;
    private String phone;
    private String email;
    private String password;
    private String providerNumber;
    private String prescriberNumber;
    @JsonIgnore
    private Collection<Shift> shifts;
    //    private Collection<VisitNote> visit_notes;
    private Collection<Role> roles;
}
