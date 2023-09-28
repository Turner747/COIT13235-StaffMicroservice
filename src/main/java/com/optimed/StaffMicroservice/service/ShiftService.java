package com.optimed.StaffMicroservice.service;

import com.optimed.StaffMicroservice.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ShiftService {
    @Autowired
    private ShiftRepository repo;

    public ShiftService(ShiftRepository repo) {
        super();
        this.repo = repo;
    }
}
