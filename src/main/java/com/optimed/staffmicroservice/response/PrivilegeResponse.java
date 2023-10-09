package com.optimed.staffmicroservice.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimed.staffmicroservice.model.Role;
import lombok.Data;

import java.util.Collection;
@Data
public class PrivilegeResponse {
    private Long id;
    private String name;
    @JsonIgnore
    private Collection<Role> roles;
}
