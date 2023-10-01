package com.optimed.staffmicroservice.response;

import com.optimed.staffmicroservice.model.Role;
import lombok.Data;

import java.util.Collection;
@Data
public class PrivilegeResponse {
    private Long id;
    private String name;
    private Collection<Role> roles;
}
