package com.optimed.StaffMicroservice.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimed.StaffMicroservice.model.Privilege;
import com.optimed.StaffMicroservice.model.Staff;
import lombok.Data;

import java.util.Collection;
@Data
public class RoleResponse {
    private long id;
    private String name;
    @JsonIgnore
    private Collection<Staff> staff;
    private Collection<Privilege> privileges;
}
