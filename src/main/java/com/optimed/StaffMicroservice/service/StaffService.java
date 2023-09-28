package com.optimed.StaffMicroservice.service;

import com.optimed.StaffMicroservice.model.Staff;
import com.optimed.StaffMicroservice.repository.StaffRepository;
import com.optimed.StaffMicroservice.response.StaffResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private StaffRepository repo;

    @Autowired
    private ModelMapper mapper;

//    public StaffService() {
//        super();
//    }

    public StaffService(StaffRepository staffRepo) {
        super();
        this.repo = staffRepo;
    }
    public List<Staff> getAllStaffs() {
        return repo.findAll();
    }

//    public List<Staff> getAllStaffByRole(String role) {
//        return repo.getAllStaffByRole(role);
//    }
    public StaffResponse getStaffById(long id) {
        Optional<Staff> optional = repo.findById(id);
        if(optional.isPresent())
            return mapper.map(optional, StaffResponse.class);
        return null;
    }
    public Staff saveStaff(Staff staff) {
        return repo.save(staff);
    }
}
