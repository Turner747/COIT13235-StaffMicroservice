package com.optimed.staffmicroservice.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PasswordResponse {
    private long id;
    private String password;
    @JsonIgnore
    private StaffResponse staff;
}
