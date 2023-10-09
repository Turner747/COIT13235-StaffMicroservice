package com.optimed.staffmicroservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.optimed.staffmicroservice.model.Role;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StaffResponse {
    private long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String street;
    private String suburb;
    private String state;
    private String postcode;
    private String phone;
    private String email;
    private String password;
    private String providerNumber;
    private String prescriberNumber;
    @JsonProperty("role")
    private Role role;
}
