package com.optimed.StaffMicroservice.service;

import com.optimed.StaffMicroservice.model.Staff;
import com.optimed.StaffMicroservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepo;

    public List<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    public Staff getStaffById(long staffid) {
        Optional<Staff> optional = staffRepo.findById(staffid);
        Staff staff = null;
        if(optional.isPresent())
            staff = optional.get();
        return staff;
    }
}
